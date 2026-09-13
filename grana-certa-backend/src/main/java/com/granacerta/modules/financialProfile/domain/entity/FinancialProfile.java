package com.granacerta.modules.financialProfile.domain.entity;

import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;
import com.granacerta.modules.user.domain.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FinancialProfile {
    private UUID id;
    private User user;
    private Objective objective;
    private ControlLevel controlLevel;
    private MainDifficulty mainDifficulty;
    private PreferredView preferredView;
    private LocalDateTime createdAt;
    private BigDecimal monthlyIncome;
    private LocalDateTime updatedAt;
    private boolean active;



    public static FinancialProfile create(User user, Objective objective, ControlLevel controlLevel, MainDifficulty mainDifficulty, PreferredView preferredView,BigDecimal monthlyIncome) {
        FinancialProfile financialProfile = new FinancialProfile();
        financialProfile.user = user;
        financialProfile.objective = objective;
        financialProfile.controlLevel = controlLevel ;
        financialProfile.mainDifficulty = mainDifficulty;
        financialProfile.preferredView = preferredView;
        financialProfile.monthlyIncome = monthlyIncome;
        financialProfile.active = true;

        return financialProfile;
    }

    public void update(
            BigDecimal monthlyIncome,
            Objective objective,
            ControlLevel controlLevel,
            MainDifficulty mainDifficulty,
            PreferredView preferredView
    ) {
        if (monthlyIncome != null) {
            this.monthlyIncome = monthlyIncome;
        }

        if (objective != null) {
            this.objective = objective;
        }

        if (controlLevel != null) {
            this.controlLevel = controlLevel;
        }

        if (mainDifficulty != null) {
            this.mainDifficulty = mainDifficulty;
        }

        if (preferredView != null) {
            this.preferredView = preferredView;
        }
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void desactive(){
        this.active = false;
    }


    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public ControlLevel getControlLevel() {
        return controlLevel;
    }

    public void setControlLevel(ControlLevel controlLevel) {
        this.controlLevel = controlLevel;
    }

    public MainDifficulty getMainDifficulty() {
        return mainDifficulty;
    }

    public void setMainDifficulty(MainDifficulty mainDifficulty) {
        this.mainDifficulty = mainDifficulty;
    }

    public PreferredView getPreferredView() {
        return preferredView;
    }

    public void setPreferredView(PreferredView preferredView) {
        this.preferredView = preferredView;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialProfile that = (FinancialProfile) o;
        return Objects.equals(user.getId(), that.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId());
    }
}
