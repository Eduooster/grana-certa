package com.granacerta.modules.financialProfile.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class FinancialProfile {
    private Long userId;
    private String objective;
    private String controlLevel;
    private String mainDifficulty;
    private String preferredView;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialProfile that = (FinancialProfile) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
