package com.granacerta.modules.financialAccount.infra.persistence.adapter;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialAccount.infra.persistence.mapper.FinancialAccountMapper;
import com.granacerta.modules.financialAccount.infra.persistence.repository.FinancialAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

@RequiredArgsConstructor

public class FinancialAccountImpl implements FinancialAccountRepository {

    private final FinancialAccountJpaRepository financialAccountJpaRepository;

    private final FinancialAccountMapper financialAccountMapper;


    @Override
    public void save(FinancialAccount financialAccount) {
        financialAccountJpaRepository.save(
                financialAccountMapper.toEntity(financialAccount)

        );

    }

    @Override
    public Optional<FinancialAccount> findByIdAndUserIdAndActiveTrue(UUID accountId, UUID userId, FinancialAccountStatus status) {
       return financialAccountJpaRepository.findByIdAndUserIdAndStatus(accountId, userId, status).map(financialAccountMapper::toDomain);
    }
}
