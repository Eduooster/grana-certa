package com.granacerta.modules.recurrence.infra.persistence.mapper;

import com.granacerta.modules.recurrence.domain.entity.Recurrence;
import com.granacerta.modules.recurrence.infra.persistence.entity.RecurrenceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecurrenceEntityMapper {
    Recurrence toDomain(RecurrenceEntity recurrenceEntity);
    RecurrenceEntity toEntity(Recurrence recurrence);
}
