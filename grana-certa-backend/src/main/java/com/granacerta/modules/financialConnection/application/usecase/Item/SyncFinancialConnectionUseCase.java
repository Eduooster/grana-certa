package com.granacerta.modules.financialConnection.application.usecase.Item;

import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.exception.SyncFailedException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class SyncFinancialConnectionUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;
    private final ImportTransactionsUseCase importTransactionsUseCase;

    public SyncFinancialConnectionUseCase(FinancialConnectionRepository financialConnectionRepository, ImportFinancialAccountsUseCase importFinancialAccountsUseCase, ImportTransactionsUseCase importTransactionsUseCase) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.importFinancialAccountsUseCase = importFinancialAccountsUseCase;
        this.importTransactionsUseCase = importTransactionsUseCase;
    }

    @Transactional
    public void execute(UUID connectionId) {

        FinancialConnection connection =
                financialConnectionRepository
                        .findById(connectionId)
                        .orElseThrow(() ->
                                new FinancialConnectionNotFoundException(
                                        "Financial Connection not found: "
                                                + connectionId
                                )
                        );

        try {

            connection.startSync();

            financialConnectionRepository.save(connection);

            String externalId = connection.getExternalId();

            importFinancialAccountsUseCase.execute(externalId);


            importTransactionsUseCase.execute(externalId);

            connection.completeSync();

            financialConnectionRepository.save(connection);

        } catch (Exception e) {

            connection.failSync();

            financialConnectionRepository.save(connection);

            throw new SyncFailedException(
                    "Failed to synchronize financial connection: "
                            + connectionId , e
            );
        }
    }
}