package com.granacerta.modules.financialInstitution.application.usecase;

import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.domain.repository.FinancialInstitutionRepository;

import java.util.UUID;

public class CreateFinancialInstitutionUseCase {
    private final FinancialInstitutionRepository financialInstitutionRepository;

    public CreateFinancialInstitutionUseCase(FinancialInstitutionRepository financialInstitutionRepository) {
        this.financialInstitutionRepository = financialInstitutionRepository;
    }

    public UUID execute(CreateFinancialInstitutionCommand command) {
        FinancialInstitution financialInstitution = FinancialInstitution.create(
                command.connectorId(),command.name(),command.imageUrl()
        );
        FinancialInstitution savedFinancialInstitution = financialInstitutionRepository.save(financialInstitution);
        return savedFinancialInstitution.getId();
    }
}
