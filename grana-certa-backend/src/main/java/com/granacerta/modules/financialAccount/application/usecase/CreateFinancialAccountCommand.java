package com.granacerta.modules.financialAccount.application.usecase;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateFinancialAccountCommand(
        UUID userId,

        String name,


        FinancialAccountType type,

        BigDecimal balance,

        Integer closingDay,

        Integer dueDay
) {
}