package com.granacerta.modules.transaction.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record GetTransactionsRequest(
        @Min(0)
        int page,

        @Min(1)
        @Max(100)
        int size
) {
}