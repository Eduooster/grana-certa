package com.granacerta.modules.transfer.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransferRequest(
        UUID sourceAccountId,
        UUID destinationAccountId,
        BigDecimal amount
) {
}