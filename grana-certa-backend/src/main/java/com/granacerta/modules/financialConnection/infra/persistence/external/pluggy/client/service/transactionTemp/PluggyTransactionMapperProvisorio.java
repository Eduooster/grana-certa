package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service.transactionTemp;


import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class PluggyTransactionMapperProvisorio {

    public List<ExternalTransaction> toExternalTransactions(
            List<PluggyTransactionResponse> transactions
    ) {
        return transactions.stream()
                .map(this::toExternalTransaction)
                .toList();
    }

    private ExternalTransaction toExternalTransaction(
            PluggyTransactionResponse transaction
    ) {

        return new ExternalTransaction(
                transaction.id(),
                transaction.accountId(),
                transaction.description(),
                transaction.amount(),
                OffsetDateTime
                        .parse(transaction.date())
                        .toLocalDate(),
                mapType(transaction.type())
        );
    }

    private TransactionType mapType(String type) {

        return switch (type) {
            case "DEBIT" -> TransactionType.EXPENSE;
            case "CREDIT" -> TransactionType.INCOME;

            default -> throw new IllegalArgumentException(
                    "Unsupported Pluggy transaction type: " + type
            );
        };
    }
}
