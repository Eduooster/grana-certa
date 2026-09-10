package com.granacerta.modules.recurrence.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Recurrence {
    private Long id;
    private Long userId;
    private Long accountId;
    private Long categoryId;
    private String type;
    private BigDecimal amount;
    private String description;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextOccurrence;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurrence that = (Recurrence) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
