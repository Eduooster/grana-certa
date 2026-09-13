package com.granacerta.modules.financialProfile.application.usecase;

import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record GetFinancialProfileResult(

        UUID id,
        BigDecimal monthlyIncome,
        Objective objective,
        ControlLevel controlLevel,
        MainDifficulty mainDifficulty,
        PreferredView preferredView,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
