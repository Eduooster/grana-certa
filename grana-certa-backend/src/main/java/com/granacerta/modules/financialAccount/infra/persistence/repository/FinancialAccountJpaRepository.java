package com.granacerta.modules.financialAccount.infra.persistence.repository;

import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.infra.persistence.entity.FinancialAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialAccountJpaRepository extends JpaRepository<FinancialAccountEntity, UUID> {
    Optional<FinancialAccountEntity
                > findByIdAndUserIdAndStatus(UUID accountId, UUID userId, FinancialAccountStatus status);
}
