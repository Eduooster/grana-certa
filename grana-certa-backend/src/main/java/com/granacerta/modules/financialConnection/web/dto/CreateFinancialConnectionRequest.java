package com.granacerta.modules.financialConnection.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateFinancialConnectionRequest(
        String itemId
) { }
