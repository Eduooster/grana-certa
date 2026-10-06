package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain;

import java.util.List;
import java.util.UUID;

public record PluggyWebhookCommand(
        PluggyWebhookEvent event,
        String eventId,
        String triggeredBy,
        String itemId,
        UUID clientUserId,
        String accountId,
        String connectorId,
        String paymentRequestId,
        String paymentIntentId,
        List<String> transactionIds,
        Integer transactionsCount,
        String createdTransactionsLink,
        PluggyErrorDetails error,
        PluggyDataDetails data
) {
    public record PluggyErrorDetails(
            String code,
            String message,
            String parameter,
            String description,
            String detail
    ) {}

    public record PluggyDataDetails(
            String status
    ) {}
}