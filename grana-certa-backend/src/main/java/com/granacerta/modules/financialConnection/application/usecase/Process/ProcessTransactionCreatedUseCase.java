package com.granacerta.modules.financialConnection.application.usecase.Process;

import com.granacerta.modules.category.application.usecase.ResolveTransactionCategoryUseCase;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.financialConnection.application.gateway.FinancialTransactionProviderGateway;
import com.granacerta.modules.financialConnection.application.usecase.ProcessTransactionCreatedCommand;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j

public class ProcessTransactionCreatedUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialTransactionProviderGateway transactionProvider;
    private final TransactionRepository transactionRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final ResolveTransactionCategoryUseCase resolveTransactionCategoryUseCase;

    public ProcessTransactionCreatedUseCase(
            FinancialConnectionRepository financialConnectionRepository,
            FinancialTransactionProviderGateway transactionProvider,
            TransactionRepository transactionRepository, FinancialAccountRepository financialAccountRepository, ResolveTransactionCategoryUseCase resolveTransactionCategoryUseCase
    ) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.transactionProvider = transactionProvider;
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.resolveTransactionCategoryUseCase = resolveTransactionCategoryUseCase;
    }

    @Transactional
    public void execute(ProcessTransactionCreatedCommand command) {

        FinancialConnection connection =
                financialConnectionRepository.findByExternalId(command.itemId())
                        .orElseThrow(() -> new FinancialConnectionNotFoundException("No financial connection found for itemId: " + command.itemId()));


        FinancialAccount account =
                financialAccountRepository
                        .findByExternalId(command.accountId())
                        .orElseThrow(
                                () -> new FinancialAccountNotFoundException("No financial account found for accountId: " + command.accountId())
                        );

        List<ExternalTransaction> transactions =
                transactionProvider.getCreatedTransactions(
                        command.createdTransactionsLink()
                );
        int savedCount = 0;
        int skippedCount = 0;

        for (ExternalTransaction externalTransaction : transactions) {


            if (transactionRepository.existsByAccountIdAndExternalId(
                    account.getId(), externalTransaction.externalId()
            )) {

                skippedCount++;
                continue;
            }


            UUID categoryId = resolveTransactionCategoryUseCase
                    .execute(externalTransaction.description())
                    .orElse(null);

            Transaction transaction =
                    Transaction.createFromProvider(
                            connection.getUserId(),
                            account.getId(),
                            externalTransaction.externalId(),
                            externalTransaction.type(),
                            externalTransaction.amount(),
                            externalTransaction.transactionDate(),
                            externalTransaction.description(),
                            categoryId);

            transactionRepository.save(transaction);
            savedCount++;

        }

        connection.updateLastSync();

        log.info("Finished execution of ProcessTransactionCreatedCommand. Saved: {}, Skipped (duplicates): {}",
                savedCount, skippedCount);
    }
}