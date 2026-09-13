package com.granacerta.modules.financialProfile.application.usecase;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.domain.exception.FinancialProfileNotFoundException;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;

public class DeleteFinancialProfileUseCase {

    private final FinancialProfileRepository financialProfileRepository;

    public DeleteFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    public void execute(
            DeleteFinancialProfileCommand command
    ) {

        FinancialProfile profile = financialProfileRepository.findByUserId(command.userId()).orElseThrow(

                () -> new FinancialProfileNotFoundException("Financial Profile not found")
        );

        profile.desactive();
        financialProfileRepository.save(profile);

    }
}
