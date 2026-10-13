package com.granacerta.modules.financialConnection.application.usecase;

import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher.ItemStatusDispatcher;

public class HandleFinancialConnectionUpdateUseCase {

    private final ItemStatusDispatcher itemStatusDispatcher;
    private final FinancialConnectionProviderGateway financialConnectionProviderGateway;

    public HandleFinancialConnectionUpdateUseCase(ItemStatusDispatcher itemStatusDispatcher, FinancialConnectionProviderGateway financialConnectionProviderGateway) {
        this.itemStatusDispatcher = itemStatusDispatcher;
        this.financialConnectionProviderGateway = financialConnectionProviderGateway;
    }

    public void execute(String externalId ) {
        ExternalFinancialConnection externalConnection =
                financialConnectionProviderGateway.getItemDetails(externalId);

        itemStatusDispatcher.dispatch(externalConnection);
    }
}
