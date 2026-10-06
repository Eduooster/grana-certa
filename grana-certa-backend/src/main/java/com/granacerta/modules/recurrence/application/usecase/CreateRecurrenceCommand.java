package com.granacerta.modules.recurrence.application.usecase;

import com.granacerta.modules.recurrence.domain.enums.RecurrenceFrequency;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateRecurrenceCommand(
        UUID userId,
        UUID accountId,
        UUID categoryId,
        TransactionType type,
        BigDecimal amount,
        String description,

        RecurrenceFrequency frequency,


        Integer interval,

        LocalDate startDate,
        LocalDate endDate

) {}
