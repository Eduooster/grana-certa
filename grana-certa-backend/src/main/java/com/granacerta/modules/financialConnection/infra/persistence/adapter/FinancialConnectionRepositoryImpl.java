package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.financialConnection.infra.persistence.mapper.FinancialConnectionEntityMapper;
import com.granacerta.modules.financialConnection.infra.persistence.repository.FinancialConnectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FinancialConnectionRepositoryImpl implements FinancialConnectionRepository {
    private final FinancialConnectionJpaRepository financialConnectionJpaRepository;
    private final FinancialConnectionEntityMapper financialConnectionEntityMapper;

    @Override
    public FinancialConnection save(FinancialConnection financialConnection) {
        return financialConnectionEntityMapper.toDomain(
                financialConnectionJpaRepository.save(financialConnectionEntityMapper.toEntity(financialConnection))
        );
    }



    @Override
    public Optional<FinancialConnection> findByExternalId(String externalId) {
        return financialConnectionJpaRepository.findByExternalId(externalId).map(financialConnectionEntityMapper::toDomain);
    }

    @Override
    public Optional<FinancialConnection> findById(UUID connectionId) {
        return financialConnectionJpaRepository.findById(connectionId).map(financialConnectionEntityMapper::toDomain);
    }
}
