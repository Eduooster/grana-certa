package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto;

import ai.pluggy.client.response.ExecutionStatus;
import ai.pluggy.client.response.ItemStatus;
import ai.pluggy.client.response.ItemStatusDetail;
import com.granacerta.modules.financialConnection.domain.enums.ConnectionExecutionResult;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;

public record ExternalFinancialConnection(
        String externalId,
        String userId,
        Long connectorId,
        String institutionName,
        String institutionImageUrl,
        String health,
        String type,
        FinancialConnectionStatus financialConnectionStatus ,
        ConnectionExecutionResult connectionExecutionResult,
        ItemStatusDetail itemStatusDetail//

) {

}
