package com.granacerta.modules.financialConnection.application.gateway;

import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExternalTransaction(
        String externalId,
        String accountExternalId,
        String description,
        BigDecimal amount,
        LocalDate transactionDate,
        TransactionType type
) {
}