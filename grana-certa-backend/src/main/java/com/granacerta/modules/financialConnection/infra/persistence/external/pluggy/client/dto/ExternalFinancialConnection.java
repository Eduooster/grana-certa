package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto;

import ai.pluggy.client.response.ItemStatus;

public record ExternalFinancialConnection(
        String externalId,
        String userId,
        Long connectorId,
        String institutionName,
        String institutionImageUrl,
        String health,
        String type,
        ItemStatus status
) {

}
