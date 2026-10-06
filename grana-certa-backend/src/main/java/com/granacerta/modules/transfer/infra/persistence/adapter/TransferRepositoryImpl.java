package com.granacerta.modules.transfer.infra.persistence.adapter;

import com.granacerta.modules.transfer.domain.entity.Transfer;
import com.granacerta.modules.transfer.domain.repository.TransferRepository;
import com.granacerta.modules.transfer.infra.persistence.mapper.TranferMapper;
import com.granacerta.modules.transfer.infra.persistence.repository.TransferJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {

    private final TransferJpaRepository transferJpaRepository;
    private final TranferMapper transferMapper;

    @Override
    public Transfer save(Transfer transfer) {
        return transferMapper.toDomain(transferJpaRepository.save(transferMapper.toEntity(transfer)));
    }

    @Override
    public Optional<Transfer> findByIdAndUserIdAndActiveTrue(UUID tranferId, UUID userId) {
        return transferJpaRepository.findByIdAndUserIdAndActiveTrue(
                tranferId, userId
        ).map(transferMapper::toDomain);

    }
}
