package com.granacerta.modules.financialProfile.application.usecase;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.domain.exception.FinancialProfileNotFoundException;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import com.granacerta.modules.financialProfile.web.mapper.FinancialProfileWebMapper;
import com.granacerta.modules.user.domain.exception.UserNotFoundException;

public class GetFinancialProfileUseCase {
    private FinancialProfileRepository financialProfileRepository   ;

    public GetFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }

    public GetFinancialProfileResult execute(
            GetFinancialProfileCommand command
    ) {
        FinancialProfile financialProfile =
                financialProfileRepository.findByUserId(command.userId())
                        .orElseThrow(()-> new FinancialProfileNotFoundException("Financial profile not found "));

        return new GetFinancialProfileResult(
                financialProfile.getId(),
                financialProfile.getMonthlyIncome(),
                financialProfile.getObjective(),
                financialProfile.getControlLevel(),
                financialProfile.getMainDifficulty(),
                financialProfile.getPreferredView(),
                financialProfile.getCreatedAt(),
                financialProfile.getUpdatedAt()


        );
    }
}
