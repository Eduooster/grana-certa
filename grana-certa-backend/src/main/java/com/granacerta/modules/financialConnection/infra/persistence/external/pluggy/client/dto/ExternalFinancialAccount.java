package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import okhttp3.internal.Internal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExternalFinancialAccount(
        String externalId,
        String name,
        FinancialAccountType type,
        BigDecimal balance,
        String subType,
        BigDecimal creditLimit,
        BigDecimal availableCreditLimit,
        Integer closeDate,
        Integer dueDate
) {
}
