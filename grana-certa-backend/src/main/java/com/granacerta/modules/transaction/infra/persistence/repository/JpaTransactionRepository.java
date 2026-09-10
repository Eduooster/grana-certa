package com.granacerta.modules.transaction.infra.persistence.repository;

import com.granacerta.modules.transaction.infra.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
