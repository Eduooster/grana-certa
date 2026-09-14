package com.granacerta.modules.financialAccount.domain.repository;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialAccountRepository  {
    void save(FinancialAccount financialAccount);
    Optional<FinancialAccount> findByIdAndUserIdAndActiveTrue(
            UUID accountId,
            UUID userId, FinancialAccountStatus status
            );
}
