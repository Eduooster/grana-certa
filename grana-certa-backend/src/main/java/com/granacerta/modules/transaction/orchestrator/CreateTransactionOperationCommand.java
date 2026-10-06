package com.granacerta.modules.transaction.orchestrator;

import com.granacerta.modules.recurrence.RecurrenceData;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionOperationCommand(
        UUID userId,
        UUID accountId,
        UUID categoryId,
        TransactionType type,
        BigDecimal amount,
        LocalDate transactionDate,
        String description,
        RecurrenceData recurrence
) {
}
