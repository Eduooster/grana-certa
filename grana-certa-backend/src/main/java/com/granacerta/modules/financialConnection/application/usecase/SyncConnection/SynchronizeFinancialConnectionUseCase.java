package com.granacerta.modules.financialConnection.application.usecase.SyncConnection;

import ai.pluggy.client.response.ItemStatus;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportFinancialAccountsUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportTransactionsUseCase;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher.ItemStatusDispatcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizeFinancialConnectionUseCase {

    private final FinancialConnectionRepository financialConnectionRepository;
    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;
    private final ImportTransactionsUseCase importTransactionsUseCase;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;



    public SynchronizeFinancialConnectionUseCase(
            FinancialConnectionRepository financialConnectionRepository,
            ImportFinancialAccountsUseCase importFinancialAccountsUseCase,
            ImportTransactionsUseCase importTransactionsUseCase, FinancialConnectionProviderGateway financialConnectionProviderGateway
    ) {
        this.financialConnectionRepository = financialConnectionRepository;
        this.importFinancialAccountsUseCase = importFinancialAccountsUseCase;
        this.importTransactionsUseCase = importTransactionsUseCase;
        this.financialConnectionProviderGateway = financialConnectionProviderGateway;

    }


    public void execute(ExternalFinancialConnection externalFinancialConnection  ) {

        FinancialConnection connection =
                financialConnectionRepository
                        .findByExternalId(externalFinancialConnection.externalId())
                        .orElseThrow(() ->
                                new FinancialConnectionNotFoundException(
                                        "Financial Connection not found: " + externalFinancialConnection.externalId()
                                )
                        );

        importFinancialAccountsUseCase.execute(connection.getExternalId());

        importTransactionsUseCase.execute(connection.getExternalId());

        connection.updateLastSync();

        financialConnectionRepository.save(connection);
    }
}