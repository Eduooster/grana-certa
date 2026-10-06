package com.granacerta.modules.financialConnection.application.usecase;

import java.util.UUID;

public record CreateFinancialConnectionCommand(
        UUID userId,
        String itemId
) { }
