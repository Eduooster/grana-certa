package com.granacerta.modules.financialConnection.application.usecase.Item;


import ai.pluggy.client.response.TransactionsResponse;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.financialConnection.application.gateway.FinancialTransactionProviderGateway;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class ImportTransactionsUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final FinancialTransactionProviderGateway financialTransactionProviderGateway;
    private final FinancialAccountRepository financialAccountRepository;
    private final TransactionRepository transactionRepository;

    public ImportTransactionsUseCase(FinancialConnectionRepository financialConnectionRepository, FinancialTransactionProviderGateway financialTransactionProviderGateway, FinancialAccountRepository financialAccountRepository, TransactionRepository transactionRepository) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.financialTransactionProviderGateway = financialTransactionProviderGateway;
        this.financialAccountRepository = financialAccountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    public void execute(String connectionId) {

        FinancialConnection connection =
                financialConnectionRepository
                        .findByExternalId(connectionId)
                        .orElseThrow(() ->
                                new FinancialConnectionNotFoundException(
                                        "Financial Connection not found for item: "
                                                + connectionId
                                )
                        );

        List<FinancialAccount> accounts =
                financialAccountRepository.findAllByConnectionId(
                        connection.getId()
                );

        for (FinancialAccount account : accounts) {



            List<ExternalTransaction> externalTransactions =
                    financialTransactionProviderGateway
                            .fetchTransactions(account.getExternalId());


            for (ExternalTransaction externalTransaction : externalTransactions) {



                if (transactionRepository
                        .findByExternalId(externalTransaction.externalId())
                        .isPresent()) {


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


            }


        }



    }}