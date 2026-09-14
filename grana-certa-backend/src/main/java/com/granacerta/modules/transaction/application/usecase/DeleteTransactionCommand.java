package com.granacerta.modules.transaction.application.usecase;

import java.util.UUID;

public record DeleteTransactionCommand(
        UUID transactionId,
        UUID userId
) {
}