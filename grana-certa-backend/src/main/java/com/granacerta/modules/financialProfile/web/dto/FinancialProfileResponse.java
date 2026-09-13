package com.granacerta.modules.financialProfile.web.dto;

import com.granacerta.modules.financialProfile.domain.enums.ControlLevel;
import com.granacerta.modules.financialProfile.domain.enums.MainDifficulty;
import com.granacerta.modules.financialProfile.domain.enums.Objective;
import com.granacerta.modules.financialProfile.domain.enums.PreferredView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record FinancialProfileResponse( UUID id,
                                        BigDecimal monthlyIncome,
                                        Objective objective,
                                        ControlLevel controlLevel,
                                        MainDifficulty mainDifficulty,
                                        PreferredView preferredView,
                                        LocalDateTime createdAt,
                                        LocalDateTime updatedAt) {
}
