package com.granacerta.modules.transfer.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransferCommand(
        UUID userId,
        UUID sourceAccountId,
        UUID destinationAccountId,
        BigDecimal amount
) {
}