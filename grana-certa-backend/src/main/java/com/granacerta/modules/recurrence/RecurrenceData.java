package com.granacerta.modules.recurrence;

import com.granacerta.modules.recurrence.domain.enums.RecurrenceFrequency;

import java.time.LocalDate;

public record RecurrenceData(
        RecurrenceFrequency frequency,
        Integer intervalValue,
        LocalDate startDate,
        LocalDate endDate
) {}
