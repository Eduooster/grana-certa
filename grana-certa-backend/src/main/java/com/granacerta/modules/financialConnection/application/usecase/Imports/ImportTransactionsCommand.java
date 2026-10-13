package com.granacerta.modules.financialConnection.application.usecase.Imports;

import java.util.List;

public record ImportTransactionsCommand(
        String itemId,
        List<String> transactionIds
) {
}