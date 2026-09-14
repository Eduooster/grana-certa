package com.granacerta.modules.transaction.infra.persistence.adapter;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import com.granacerta.modules.transaction.infra.persistence.mapper.TransactionEntityMapper;
import com.granacerta.modules.transaction.infra.persistence.repository.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;

    private final TransactionEntityMapper transactionEntityMapper;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionEntityMapper.toDomain(jpaRepository.save(transactionEntityMapper.toEntity(transaction)));
    }

    @Override
    public Page<Transaction> findAllByUserIdAndActiveTrue(UUID userId, Pageable pageable) {
        return jpaRepository
                .findAllByUserIdAndActiveTrue(userId, pageable)
                .map(transactionEntityMapper::toDomain);
    }

    @Override
    public Optional<Transaction> findByIdAndUserIdAndActiveTrue(UUID transactionId   , UUID userId) {
        return jpaRepository
                .findByIdAndUserIdAndActiveTrue(transactionId,userId)
                .map(transactionEntityMapper::toDomain);
    }
}
