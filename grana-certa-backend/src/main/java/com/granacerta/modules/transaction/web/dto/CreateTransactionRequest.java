package com.granacerta.modules.transaction.web.dto;

import com.granacerta.modules.transaction.domain.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionRequest(
        UUID accountId,

        UUID categoryId,

        @NotNull
        TransactionType type,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        LocalDate transactionDate,

        @Size(max = 255)
        String description
) {
}
