package com.granacerta.modules.financialConnection.application.usecase.SyncConnection;

import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;

public  record UpdateConnectionStatusCommand(
        String itemId,
        FinancialConnectionStatus status
) {
}
