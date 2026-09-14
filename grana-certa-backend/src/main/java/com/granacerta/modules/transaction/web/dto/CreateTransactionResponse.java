package com.granacerta.modules.transaction.web.dto;

import com.granacerta.modules.transaction.domain.enums.TransactionSource;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransactionResponse(
        UUID id,
        UUID accountId,
        UUID categoryId,
        TransactionType type,
        BigDecimal amount,
        LocalDate transactionDate,
        String description,
        TransactionSource source,
        LocalDateTime createdAt
) {
}
