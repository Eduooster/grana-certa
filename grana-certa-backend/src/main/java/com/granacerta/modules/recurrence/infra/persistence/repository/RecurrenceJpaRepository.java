package com.granacerta.modules.recurrence.infra.persistence.repository;

import com.granacerta.modules.recurrence.infra.persistence.entity.RecurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurrenceJpaRepository extends JpaRepository<RecurrenceEntity, Long> {
}
