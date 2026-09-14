package com.granacerta.modules.transaction.application.usecase;

import java.util.UUID;

public record GetTransactionsCommand(
        UUID userId,
        int page,
        int size
) {
}
