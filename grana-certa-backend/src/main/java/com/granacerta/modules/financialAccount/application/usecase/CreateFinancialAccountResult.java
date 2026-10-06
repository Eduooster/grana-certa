package com.granacerta.modules.financialAccount.application.usecase;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFinancialAccountResult(
        UUID id,
        UUID userId,
        String name,
        FinancialAccountType type,
        FinancialAccountStatus active,
        LocalDateTime createdAt
) {
}
