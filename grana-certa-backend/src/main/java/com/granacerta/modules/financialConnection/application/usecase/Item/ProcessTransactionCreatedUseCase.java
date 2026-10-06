package com.granacerta.modules.financialConnection.application.usecase.Item;

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

import java.time.LocalDateTime;
import java.util.List;
@Slf4j

public class ProcessTransactionCreatedUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialTransactionProviderGateway transactionProvider;
    private final TransactionRepository transactionRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public ProcessTransactionCreatedUseCase(
            FinancialConnectionRepository financialConnectionRepository,
            FinancialTransactionProviderGateway transactionProvider,
            TransactionRepository transactionRepository, FinancialAccountRepository financialAccountRepository
    ) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.transactionProvider = transactionProvider;
        this.transactionRepository = transactionRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    @Transactional
    public void execute(ProcessTransactionCreatedCommand command) {
        log.info("Starting execution of ProcessTransactionCreatedCommand for itemId: {} and accountId: {}",
                command.itemId(), command.accountId());

        FinancialConnection connection =
                financialConnectionRepository.findByExternalId(command.itemId())
                        .orElseThrow(() -> {
                            log.warn("Financial connection not found for itemId: {}", command.itemId());
                            return new FinancialConnectionNotFoundException("No financial connection found for itemId: " + command.itemId());
                        });

        log.debug("Found financial connection with internal ID: {} for itemId: {}", connection.getId(), command.itemId());

        FinancialAccount account =
                financialAccountRepository
                        .findByExternalId(command.accountId())
                        .orElseThrow(
                                () -> {
                                    log.warn("Financial account not found for accountId: {}", command.accountId());
                                    return new FinancialAccountNotFoundException("No financial account found for accountId: " + command.accountId());
                                }
                        );

        log.debug("Found financial account with internal ID: {} for accountId: {}", account.getId(), command.accountId());

        log.info("Fetching created transactions from provider using link: {}", command.createdTransactionsLink());
        List<ExternalTransaction> transactions =
                transactionProvider.getCreatedTransactions(
                        command.createdTransactionsLink()
                );

        log.info("Fetched {} external transaction(s) from provider.", transactions.size());

        int savedCount = 0;
        int skippedCount = 0;

        for (ExternalTransaction externalTransaction : transactions) {
            log.info("Processing external transaction with externalId: {}", externalTransaction.externalId());

            if (transactionRepository.existsByAccountIdAndExternalId(
                    account.getId(), externalTransaction.externalId()
            )) {
                log.debug("Transaction with externalId: {} already exists for connection ID: {}. Skipping.",
                        externalTransaction.externalId(), connection.getId());
                skippedCount++;
                continue;
            }

            Transaction transaction =
                    Transaction.createFromProvider(
                            connection.getUserId(),
                            account.getId(),
                            externalTransaction.externalId(),
                            externalTransaction.type(),
                            externalTransaction.amount(),
                            externalTransaction.transactionDate(),
                            externalTransaction.description()
                    );

            transactionRepository.save(transaction);
            savedCount++;
            log.trace("Successfully saved transaction with externalId: {}", externalTransaction.externalId());
        }

        connection.setLastSyncedAt(LocalDateTime.now());

        log.info("Finished execution of ProcessTransactionCreatedCommand. Saved: {}, Skipped (duplicates): {}",
                savedCount, skippedCount);
    }
}