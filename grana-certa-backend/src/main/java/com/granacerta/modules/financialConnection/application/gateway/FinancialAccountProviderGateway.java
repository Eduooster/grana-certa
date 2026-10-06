package com.granacerta.modules.financialConnection.application.gateway;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;

import java.util.List;

public interface FinancialAccountProviderGateway {

    List<ExternalFinancialAccount> fetchAccounts(
            String itemId
    );
}
