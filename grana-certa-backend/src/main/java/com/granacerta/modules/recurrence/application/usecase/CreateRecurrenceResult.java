package com.granacerta.modules.recurrence.application.usecase;

import com.granacerta.modules.recurrence.domain.enums.RecurrenceFrequency;
import com.granacerta.modules.recurrence.domain.enums.RecurrenceStatus;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateRecurrenceResult(
        UUID id,
        UUID userId,
        UUID accountId,
        UUID categoryId,
        TransactionType type,
        BigDecimal amount,
        LocalDate startDate,
        LocalDate endDate,
        RecurrenceFrequency frequency,
        Integer interval,
        String description,
        RecurrenceStatus status,
        LocalDateTime createdAt
) {}
