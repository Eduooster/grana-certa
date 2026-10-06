package com.granacerta.modules.financialConnection.application.usecase.Item;

import java.util.List;

public record ImportTransactionsCommand(
        String itemId,
        List<String> transactionIds
) {
}