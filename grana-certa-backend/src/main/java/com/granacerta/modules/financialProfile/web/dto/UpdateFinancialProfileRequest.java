package com.granacerta.modules.financialProfile.web.dto;

import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;

import java.math.BigDecimal;

public record UpdateFinancialProfileRequest(
        BigDecimal monthlyIncome,
        Objective objective,
        ControlLevel controlLevel,
        MainDifficulty mainDifficulty,
        PreferredView preferredView
) {
}