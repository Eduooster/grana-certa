package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluggyWebhookPayload(
        String event,
        String eventId,
        String triggeredBy,
        String itemId,
        String clientUserId,
        String accountId,
        String connectorId,
        String paymentRequestId,
        String paymentIntentId,
        List<String> transactionIds,
        Integer transactionsCount,
        String createdTransactionsLink,
        PluggyError error,
        PluggyData data
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PluggyError(
            String code,
            String message,
            String parameter,
            String description,
            String detail
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PluggyData(
            String status
    ) {}
}