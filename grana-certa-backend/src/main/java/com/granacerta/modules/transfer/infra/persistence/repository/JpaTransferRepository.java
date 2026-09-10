package com.granacerta.modules.transfer.infra.persistence.repository;

import com.granacerta.modules.transfer.infra.persistence.entity.TranferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransferRepository extends JpaRepository<TranferEntity, Long> {
}
