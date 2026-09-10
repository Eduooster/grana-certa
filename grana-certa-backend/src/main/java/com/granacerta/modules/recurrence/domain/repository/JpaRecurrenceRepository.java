package com.granacerta.modules.recurrence.domain.repository;

import com.granacerta.modules.recurrence.infra.persistence.entity.RecurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecurrenceRepository extends JpaRepository<RecurrenceEntity, Long> {
}
