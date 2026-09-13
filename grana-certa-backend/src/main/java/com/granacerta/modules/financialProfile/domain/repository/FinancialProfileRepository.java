package com.granacerta.modules.financialProfile.domain.repository;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;


import java.util.Optional;
import java.util.UUID;

public interface FinancialProfileRepository {
    FinancialProfile save(FinancialProfile financialProfile);

    Optional<FinancialProfile> findByUserId(UUID uuid);
}
