package com.granacerta.modules.transfer.application.usecase;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateTransferCommand(
        UUID transferId,
        UUID userId,
        UUID destinationAccountId,
        BigDecimal amount
) {
}