package com.granacerta.modules.transaction.infra.persistence.repository;

import com.granacerta.modules.transaction.infra.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;
import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    Page<TransactionEntity> findAllByUserIdAndActiveTrue(UUID userId, Pageable pageable);



    Optional<TransactionEntity> findByIdAndUserIdAndActiveTrue(UUID transactionId, UUID userId);
}
