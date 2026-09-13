package com.granacerta.modules.financialAccount.infra.persistence.adapter;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialAccount.infra.persistence.mapper.FinancialAccountMapper;
import com.granacerta.modules.financialAccount.infra.persistence.repository.FinancialAccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
