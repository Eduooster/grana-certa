package com.granacerta.modules.financialProfile.application.usecase;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.domain.exception.FinancialProfileNotFoundException;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import org.springframework.transaction.annotation.Transactional;

public class UpdateFinancialProfileUseCase {

    private final FinancialProfileRepository financialProfileRepository ;

    public UpdateFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository) {
        this.financialProfileRepository = financialProfileRepository;
    }
    @Transactional
    public UpdateFinancialProfileResult     execute(
            UpdateFinancialProfileCommand command
    ) {
        FinancialProfile financialProfile =
                financialProfileRepository.findByUserId(command.userId())
                        .orElseThrow(() ->
                                new FinancialProfileNotFoundException(
                                        "Financial profile not found"
                                )
                        );

        financialProfile.update(
                command.monthlyIncome(),
                command.objective(),
                command.controlLevel(),
                command.mainDifficulty(),
                command.preferredView()
        );

        FinancialProfile updatedFinancialProfile =
                financialProfileRepository.save(financialProfile);

        return new UpdateFinancialProfileResult(
                updatedFinancialProfile.getId(),
                updatedFinancialProfile.getMonthlyIncome(),
                updatedFinancialProfile.getObjective(),
                updatedFinancialProfile.getControlLevel(),
                updatedFinancialProfile.getMainDifficulty(),
                updatedFinancialProfile.getPreferredView(),
                updatedFinancialProfile.getCreatedAt(),
                updatedFinancialProfile.getUpdatedAt()
        );
    }
}