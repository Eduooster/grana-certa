package com.granacerta.modules.financialInstitution.infra.persistence.repository;

import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.infra.entity.FinancialInstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialInstitutionJpaRepository extends JpaRepository<FinancialInstitutionEntity, UUID> {
    Optional<FinancialInstitutionEntity> findByConnectorId(Long id);
}
