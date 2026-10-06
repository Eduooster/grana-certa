package com.granacerta.modules.financialInstitution.infra.persistence.adapter;

import com.granacerta.modules.financialInstitution.domain.entity.FinancialInstitution;
import com.granacerta.modules.financialInstitution.domain.repository.FinancialInstitutionRepository;
import com.granacerta.modules.financialInstitution.infra.mapper.FinancialInstitutionEntityMapper;
import com.granacerta.modules.financialInstitution.infra.persistence.repository.FinancialInstitutionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FinancialInstitutionRepositoryImpl implements FinancialInstitutionRepository {
    private final FinancialInstitutionJpaRepository financialInstitutionJpaRepository;
    private final FinancialInstitutionEntityMapper financialInstitutionEntityMapper;

    @Override
    public FinancialInstitution save(FinancialInstitution financialInstitution) {
        return financialInstitutionEntityMapper.toDomain(
                financialInstitutionJpaRepository.save(financialInstitutionEntityMapper.toEntity(financialInstitution))
        );
    }

    @Override
    public Optional<FinancialInstitution> findById(UUID id) {
        return financialInstitutionJpaRepository.findById(id).map(financialInstitutionEntityMapper::toDomain);
    }

    @Override
    public Optional<FinancialInstitution> findByConnectorId(Long id) {
        return financialInstitutionJpaRepository.findByConnectorId(id).map(financialInstitutionEntityMapper::toDomain);
    }
}
