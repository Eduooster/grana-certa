package com.granacerta.modules.recurrence.web.dto;

import com.granacerta.modules.recurrence.domain.enums.RecurrenceFrequency;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateRecurrenceRequest(
        UUID accountId,
        UUID categoryId,
        TransactionType type,
        BigDecimal amount,
        LocalDate startDate,
        RecurrenceFrequency frequency,
        Integer interval,
        LocalDate endDate,
        String description
) {}