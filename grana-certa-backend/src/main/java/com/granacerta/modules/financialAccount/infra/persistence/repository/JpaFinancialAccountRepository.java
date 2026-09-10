package com.granacerta.modules.financialAccount.infra.persistence.repository;

import com.granacerta.modules.financialAccount.infra.persistence.entity.FinancialAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFinancialAccountRepository extends JpaRepository<FinancialAccountEntity, Long> {
}
