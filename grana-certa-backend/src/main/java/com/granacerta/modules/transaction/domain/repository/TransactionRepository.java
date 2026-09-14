package com.granacerta.modules.transaction.domain.repository;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Page<Transaction> findAllByUserIdAndActiveTrue(
            UUID userId,
            Pageable pageable
    );


    Optional<Transaction> findByIdAndUserIdAndActiveTrue(UUID transactionId, UUID userId);
}
