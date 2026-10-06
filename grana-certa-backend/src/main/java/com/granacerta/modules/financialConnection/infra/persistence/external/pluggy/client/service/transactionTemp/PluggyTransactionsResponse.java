package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service.transactionTemp;

import java.util.List;

public record PluggyTransactionsResponse(
        List<PluggyTransactionResponse> results,
        String next
) {
}