package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.Account;
import ai.pluggy.client.response.AccountsResponse;
import ai.pluggy.client.response.CreditData;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Component
public class PluggyFinancialAccountMapper {

    public List<ExternalFinancialAccount> toExternalFinancialAccounts(
            AccountsResponse response
    ) {
        if (response == null || response.getResults() == null) {
            return List.of();
        }

        return response.getResults()
                .stream()
                .filter(Objects::nonNull)
                .map(this::toExternalFinancialAccount)
                .toList();
    }

    public ExternalFinancialAccount toExternalFinancialAccount(Account account) {
        CreditData creditData = account.getCreditData();

        BigDecimal creditLimit = null;
        BigDecimal availableCreditLimit = null;
        Integer closingDay = null;
        Integer dueDay = null;

        if (creditData != null) {
            creditLimit = toBigDecimal(creditData.getCreditLimit());
            availableCreditLimit = toBigDecimal(
                    creditData.getAvailableCreditLimit()
            );

            closingDay = extractDay(creditData.getBalanceCloseDate());
            dueDay = extractDay(creditData.getBalanceDueDate());
        }

        return new ExternalFinancialAccount(
                account.getId(),
                account.getName(),
                mapType(account.getType()),
                toBigDecimal(account.getBalance()),
                account.getSubtype(),
                creditLimit,
                availableCreditLimit,
                closingDay,
                dueDay
        );
    }

    private FinancialAccountType mapType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException(
                    "Pluggy account type cannot be null or blank"
            );
        }

        return switch (type) {
            case "BANK" -> FinancialAccountType.BANK;
            case "CREDIT" -> FinancialAccountType.CREDIT;
            default -> throw new IllegalArgumentException(
                    "Unknown Pluggy account type: " + type
            );
        };
    }

    private BigDecimal toBigDecimal(Double value) {
        return value != null
                ? BigDecimal.valueOf(value)
                : BigDecimal.ZERO;
    }

    private Integer extractDay(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        try {
            return LocalDate.parse(date).getDayOfMonth();
        } catch (DateTimeParseException exception) {
            return null;
        }
    }
}