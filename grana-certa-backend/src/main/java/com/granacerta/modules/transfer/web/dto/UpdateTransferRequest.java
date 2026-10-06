package com.granacerta.modules.transfer.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateTransferRequest(
        UUID destinationAccountId,
        BigDecimal amount
) {
}