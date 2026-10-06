package com.granacerta.modules.financialConnection.application.usecase;

public record ProcessTransactionCreatedCommand(
        String itemId,
        String accountId,
        String createdTransactionsLink
) {
}