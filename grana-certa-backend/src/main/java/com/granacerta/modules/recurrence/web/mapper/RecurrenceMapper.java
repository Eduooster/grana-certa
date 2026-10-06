package com.granacerta.modules.recurrence.web.mapper;

import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceCommand;
import com.granacerta.modules.recurrence.web.dto.CreateRecurrenceRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RecurrenceMapper {
    CreateRecurrenceCommand toCommand(UUID userId, CreateRecurrenceRequest request);
}
