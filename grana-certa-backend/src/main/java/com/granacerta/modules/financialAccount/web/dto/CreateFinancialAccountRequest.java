package com.granacerta.modules.financialAccount.web.dto;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateFinancialAccountRequest(
        @NotNull
        String name,

        @NotNull
        FinancialAccountType type,

        @NotNull
        BigDecimal balance,

        Integer closingDay,

        Integer dueDay
) {
}