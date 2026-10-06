package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;

import java.math.BigDecimal;

public record ExternalFinancialAccount(
        String externalId,
        String name,
        FinancialAccountType type,
        BigDecimal balance
) {
}
