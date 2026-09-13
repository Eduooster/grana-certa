package com.granacerta.modules.financialProfile.infra.persistence.repository;

import com.granacerta.modules.financialProfile.infra.persistence.entity.FinancialProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialProfileJpaRepository extends JpaRepository<FinancialProfileEntity, Long> {
    Optional<FinancialProfileEntity> findByUserId(UUID userId);
}
