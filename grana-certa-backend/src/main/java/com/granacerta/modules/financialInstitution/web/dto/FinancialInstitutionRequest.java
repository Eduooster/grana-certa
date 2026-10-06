package com.granacerta.modules.financialInstitution.web.dto;

import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionHealth;
import com.granacerta.modules.financialInstitution.domain.enums.FinancialInstitutionType;

public record FinancialInstitutionRequest(
        Long connectorId,
        String name,
        String imageUrl,
        FinancialInstitutionHealth health,
        FinancialInstitutionType bankType
) { }
