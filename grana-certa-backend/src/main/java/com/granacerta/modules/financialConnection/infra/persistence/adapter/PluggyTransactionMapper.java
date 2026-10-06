package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.Transaction;
import ai.pluggy.client.response.TransactionsResponse;
import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
public class PluggyTransactionMapper {

    public List<ExternalTransaction> toExternalTransactions(
            TransactionsResponse response
    ) {
        return response.getResults()
                .stream()
                .map(this::toExternalTransaction)
                .toList();
    }

    private ExternalTransaction toExternalTransaction(
            Transaction transaction
    ) {
        return new ExternalTransaction(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getDescription(),
                mapAmount(transaction.getAmount()),
                mapDate(transaction.getDate()),
                mapType(transaction.getType())
        );
    }

    private BigDecimal mapAmount(Double amount) {
        return amount != null
                ? BigDecimal.valueOf(amount)
                : null;
    }

    private LocalDate mapDate(String date) {
        return OffsetDateTime.parse(date)
                .toLocalDate();
    }

    private TransactionType mapType(
            ai.pluggy.client.response.TransactionType
                    type
    ) {
        return switch (type) {
            case DEBIT -> TransactionType.EXPENSE;
            case CREDIT -> TransactionType.INCOME;
        };
    }
}