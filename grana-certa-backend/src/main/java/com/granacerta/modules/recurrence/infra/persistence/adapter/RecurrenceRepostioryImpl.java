package com.granacerta.modules.recurrence.infra.persistence.adapter;

import com.granacerta.modules.recurrence.domain.entity.Recurrence;
import com.granacerta.modules.recurrence.domain.repository.RecurrenceRepository;
import com.granacerta.modules.recurrence.infra.persistence.entity.RecurrenceEntity;
import com.granacerta.modules.recurrence.infra.persistence.mapper.RecurrenceEntityMapper;
import com.granacerta.modules.recurrence.infra.persistence.repository.RecurrenceJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecurrenceRepostioryImpl implements RecurrenceRepository {

    private final RecurrenceEntityMapper recurrenceEntityMapper;
    private final RecurrenceJpaRepository recurrenceJpaRepository;
    @Override
    public Recurrence save(Recurrence recurrence) {
        return recurrenceEntityMapper.toDomain(recurrenceJpaRepository.save(recurrenceEntityMapper.toEntity(recurrence)));
    }
}
