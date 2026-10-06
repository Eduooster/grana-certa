package com.granacerta.modules.invoice.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record CreateInvoiceCommand(
        UUID userId,
        UUID accountId,
        YearMonth referenceMonth,
        LocalDate openingDate,
        LocalDate closingDate,
        LocalDate dueDate
) {
}