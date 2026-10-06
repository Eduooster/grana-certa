package com.granacerta.modules.financialConnection.application.gateway;

import ai.pluggy.client.response.ItemResponse;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;

import java.util.List;
import java.util.UUID;

public interface FinancialConnectionProviderGateway {
    String generateConnectionToken( UUID userId);
    ExternalFinancialConnection getItemDetails(String itemId);

    List<ExternalFinancialAccount> getAccounts(String itemId);
}
