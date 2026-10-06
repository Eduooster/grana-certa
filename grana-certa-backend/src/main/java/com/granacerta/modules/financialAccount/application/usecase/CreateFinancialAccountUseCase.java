package com.granacerta.modules.financialAccount.application.usecase;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;

public class CreateFinancialAccountUseCase {

    private final FinancialAccountRepository financialAccountRepository;

    public CreateFinancialAccountUseCase(FinancialAccountRepository financialAccountRepository) {
        this.financialAccountRepository = financialAccountRepository;
    }

    public CreateFinancialAccountResult execute(CreateFinancialAccountCommand command) {

        FinancialAccount account;

        if (FinancialAccountType.CREDIT.equals(command.type())) {
            account = FinancialAccount.createCreditCardAccount(command);
        } else {
            account = FinancialAccount.create(command);
        }

        FinancialAccount savedAccount = financialAccountRepository.save(account);

        return new CreateFinancialAccountResult(
                savedAccount.getId(),
                savedAccount.getUserId(),
                savedAccount.getName(),
                savedAccount.getType(),
                savedAccount.getStatus(),
                savedAccount.getCreatedAt()
        );
    }
}
