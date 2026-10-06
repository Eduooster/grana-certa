package com.granacerta.modules.financialInstitution.domain.repository;

import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;

import java.util.Optional;
import java.util.UUID;

public interface FinancialInstitutionRepository {
    FinancialInstitution save(FinancialInstitution financialInstitution);
    Optional<FinancialInstitution> findById(UUID id);

    Optional<FinancialInstitution> findByConnectorId(Long id);
}
