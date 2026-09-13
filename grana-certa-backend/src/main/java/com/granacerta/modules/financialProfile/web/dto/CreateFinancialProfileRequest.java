package com.granacerta.modules.financialProfile.web.dto;

import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateFinancialProfileRequest(
        @NotNull Objective objective,
        @NotNull ControlLevel controlLevel,
        @NotNull MainDifficulty mainDifficulty,
        @NotNull PreferredView preferredView,
        BigDecimal monthlyIncome
) { }