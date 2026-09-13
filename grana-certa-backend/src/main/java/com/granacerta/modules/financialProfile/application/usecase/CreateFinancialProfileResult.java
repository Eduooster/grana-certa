package com.granacerta.modules.financialProfile.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFinancialProfileResult(
        UUID id,
        LocalDateTime createdAt
) {
}
