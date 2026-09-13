package com.granacerta.modules.financialAccount.domain.repository;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import org.springframework.data.repository.CrudRepository;

public interface FinancialAccountRepository  {
    void save(FinancialAccount financialAccount);
}
