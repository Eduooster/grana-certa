package com.granacerta.modules.transaction.application.usecase;

import java.util.UUID;

public record GetTransactionCommand(
        UUID transactionId,
        UUID userId
) {
}