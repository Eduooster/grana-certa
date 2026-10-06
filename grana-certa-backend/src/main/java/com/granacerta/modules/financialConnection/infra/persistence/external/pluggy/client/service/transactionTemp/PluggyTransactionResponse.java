package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service.transactionTemp;

import java.math.BigDecimal;

public record PluggyTransactionResponse(
        String id,
        String description,
        BigDecimal amount,
        String date,
        String accountId,
        String type
) {
}