package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service.transactionTemp;

public record PluggyAuthRequest(
        String clientId,
        String clientSecret
) {
}