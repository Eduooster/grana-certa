package com.granacerta.modules.financialConnection.application.usecase.SyncConnection;

public record SyncFinancialConnectionCommand(
        String externalId,
        String status
) {
}