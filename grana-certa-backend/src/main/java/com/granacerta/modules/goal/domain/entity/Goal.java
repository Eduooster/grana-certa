package com.granacerta.modules.goal.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Goal {
    private Long id;
    private Long userId;
    private Long categoryId;
    private BigDecimal limitAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(id, goal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
