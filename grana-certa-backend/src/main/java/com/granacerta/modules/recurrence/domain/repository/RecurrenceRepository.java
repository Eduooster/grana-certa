package com.granacerta.modules.recurrence.domain.repository;

import com.granacerta.modules.recurrence.domain.entity.Recurrence;

public interface RecurrenceRepository {
    Recurrence save(Recurrence recurrence);
}
