package com.granacerta.modules.financialConnection.application.gateway;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExternalInvoice(
        String externalId,
        LocalDateTime dueDate,
        BigDecimal totalAmount,
        String currencyCode,
        BigDecimal minimumPaymentAmount,
        Boolean allowsInstallments

) {
}