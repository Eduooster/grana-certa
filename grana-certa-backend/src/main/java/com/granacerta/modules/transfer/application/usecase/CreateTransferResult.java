package com.granacerta.modules.transfer.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransferResult(
        UUID id,
        UUID userId,
        UUID sourceAccountId,
        UUID destinationAccountId,
        BigDecimal amount,
        LocalDateTime transferredAt,
        LocalDateTime createdAt
) {
}
