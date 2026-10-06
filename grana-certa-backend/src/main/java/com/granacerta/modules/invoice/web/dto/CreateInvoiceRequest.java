package com.granacerta.modules.invoice.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record CreateInvoiceRequest(
        UUID accountId,
        YearMonth referenceMonth,
        LocalDate openingDate,
        LocalDate closingDate,
        LocalDate dueDate
) {
}
