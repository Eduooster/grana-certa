package com.granacerta.modules.financialConnection.domain.repository;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;



import java.util.Optional;
import java.util.UUID;

public interface FinancialConnectionRepository {
    FinancialConnection save(FinancialConnection financialConnection);



    Optional<FinancialConnection> findByExternalId(String externalId);

    Optional<FinancialConnection> findById(UUID connectionId);
}
