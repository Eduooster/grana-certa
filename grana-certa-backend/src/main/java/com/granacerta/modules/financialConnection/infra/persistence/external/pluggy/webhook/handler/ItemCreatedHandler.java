package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import ai.pluggy.client.response.ExecutionStatus;
import ai.pluggy.client.response.ItemStatus;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportFinancialAccountsUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportTransactionsUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Process.ProcessFinancialConnectionCreatedUseCase;
import com.granacerta.modules.financialConnection.application.usecase.SyncConnection.SynchronizeFinancialConnectionUseCase;
import com.granacerta.modules.financialConnection.domain.enums.ConnectionExecutionResult;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCreatedHandler implements  PluggyEventHandler {

    private final ProcessFinancialConnectionCreatedUseCase processFinancialConnectionCreatedUseCase;
    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;
    private final ImportTransactionsUseCase importTransactionsUseCase;
    private final SynchronizeFinancialConnectionUseCase synchronizeFinancialConnectionUseCase;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;


    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.ITEM_CREATED;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {



        processFinancialConnectionCreatedUseCase.execute(command.itemId());


        ExternalFinancialConnection externalFinancialConnection= financialConnectionProviderGateway.getItemDetails(command.itemId());

        log.info(externalFinancialConnection.toString());


        if (externalFinancialConnection.financialConnectionStatus() == FinancialConnectionStatus.CONNECTED && externalFinancialConnection.connectionExecutionResult() == ConnectionExecutionResult.SUCCESS) {
            synchronizeFinancialConnectionUseCase.execute(externalFinancialConnection);
        }



    }
}
