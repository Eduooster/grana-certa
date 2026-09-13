package com.granacerta.modules.financialProfile.infra.persistence.adapter;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import com.granacerta.modules.financialProfile.infra.persistence.entity.FinancialProfileEntity;
import com.granacerta.modules.financialProfile.infra.persistence.mapper.FinancialProfileEntityMapper;
import com.granacerta.modules.financialProfile.infra.persistence.repository.FinancialProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class FinancialProfileImpl implements FinancialProfileRepository {

    private final FinancialProfileJpaRepository financialProfileJpaRepository;
    private final FinancialProfileEntityMapper financialProfileEntityMapper;

    @Override
    public FinancialProfile save(FinancialProfile financialProfile) {
        FinancialProfileEntity entity =
                financialProfileEntityMapper.toEntity(financialProfile);

        FinancialProfileEntity savedEntity =
                financialProfileJpaRepository.save(entity);

        return financialProfileEntityMapper.toDomain(
                savedEntity,
                financialProfile.getUser()
        );






    }

    @Override
    public Optional<FinancialProfile> findByUserId(UUID uuid) {

        Optional<FinancialProfileEntity> financialProfile = financialProfileJpaRepository.findByUserId(uuid);

       return financialProfile.map(financialProfileEntityMapper::toDomain);

    }
}
