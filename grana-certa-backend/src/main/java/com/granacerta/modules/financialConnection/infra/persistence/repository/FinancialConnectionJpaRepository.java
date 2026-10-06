package com.granacerta.modules.financialConnection.infra.persistence.repository;

import com.granacerta.modules.financialConnection.infra.persistence.entity.FinancialConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface FinancialConnectionJpaRepository extends JpaRepository<FinancialConnectionEntity, UUID> {
    Optional<FinancialConnectionEntity> findByExternalId(String externalId);
}
