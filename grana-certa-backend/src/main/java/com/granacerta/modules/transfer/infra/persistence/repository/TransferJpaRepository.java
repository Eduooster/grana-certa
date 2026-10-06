package com.granacerta.modules.transfer.infra.persistence.repository;

import com.granacerta.modules.transfer.infra.persistence.entity.TransferEntity;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


public interface TransferJpaRepository extends JpaRepository<TransferEntity, UUID> {
    Optional<TransferEntity
            > findByIdAndUserIdAndActiveTrue(UUID tranferId, UUID userId);
}
