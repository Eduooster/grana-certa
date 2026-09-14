package com.granacerta.modules.transaction.infra.persistence.mapper;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.infra.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionEntityMapper {
    TransactionEntity toEntity(Transaction transaction);
    Transaction toDomain(TransactionEntity transactionEntity);
}
