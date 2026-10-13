package com.granacerta.modules.financialConnection.application.usecase.Imports;

import java.util.UUID;

public record ImportCreditCardInvoicesCommand(
        String financialAccountId
) {
}