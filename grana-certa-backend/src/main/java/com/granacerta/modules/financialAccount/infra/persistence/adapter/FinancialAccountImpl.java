package com.granacerta.modules.financialAccount.infra.persistence.adapter;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialAccount.infra.persistence.mapper.FinancialAccountMapper;
import com.granacerta.modules.financialAccount.infra.persistence.repository.FinancialAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

@RequiredArgsConstructor

public class FinancialAccountImpl implements FinancialAccountRepository {

    private final FinancialAccountJpaRepository financialAccountJpaRepository;

    private final FinancialAccountMapper financialAccountMapper;


    @Override
    public FinancialAccount save(FinancialAccount financialAccount) {
        return financialAccountMapper.toDomain(financialAccountJpaRepository.save(financialAccountMapper.toEntity(financialAccount)));

    }

    @Override
    public Optional<FinancialAccount> findByIdAndUserIdAndActiveTrue(UUID accountId, UUID userId, FinancialAccountStatus status) {
       return financialAccountJpaRepository.findByIdAndUserIdAndStatus(accountId, userId, status).map(financialAccountMapper::toDomain);
    }

    @Override
    public List<FinancialAccount> findAllByConnectionId(UUID connectionId) {
        return financialAccountJpaRepository
                .findAllByConnectionId(connectionId)
                .stream()
                .map(financialAccountMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<FinancialAccount> findByExternalId(String externalId) {
        return financialAccountJpaRepository.findByExternalId(externalId).map(financialAccountMapper::toDomain);

    }
}
