package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.Bill;
import ai.pluggy.client.response.BillsResponse;
import com.granacerta.modules.financialConnection.application.gateway.ExternalInvoice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Component
public class PluggyBillMapper {

    public ExternalInvoice toExternalInvoice(Bill bill) {
        if (bill == null) {
            return null;
        }

        return new ExternalInvoice(
                requireExternalId(bill.getId()),
                toLocalDateTime(bill.getDueDate()),
                defaultAmount(bill.getTotalAmount()),
                defaultCurrencyCode(bill.getTotalAmountCurrencyCode()),
                defaultAmount(bill.getMinimumPaymentAmount()),
                bill.isAllowsInstallments()
        );
    }

    public List<ExternalInvoice> toExternalInvoices(BillsResponse response) {
        if (response == null || response.getResults() == null) {
            return List.of();
        }

        return response.getResults()
                .stream()
                .filter(Objects::nonNull)
                .map(this::toExternalInvoice)
                .toList();
    }

    private String requireExternalId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "Pluggy Bill não possui um identificador externo"
            );
        }

        return id;
    }

    private LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private BigDecimal defaultAmount(Double value) {
        return value != null
                ? BigDecimal.valueOf(value)
                : BigDecimal.ZERO;
    }

    private String defaultCurrencyCode(String currencyCode) {
        return currencyCode != null && !currencyCode.isBlank()
                ? currencyCode
                : "BRL";
    }
}