package com.granacerta.modules.financialInstitution.application.usecase;

import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionHealth;
import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionType;

import java.util.UUID;

public record CreateFinancialInstitutionCommand(
        UUID userId,
        Long connectorId,
        String name,
        String imageUrl,
        FinancialInstitutionHealth health,
        FinancialInstitutionType bankType
) { }
