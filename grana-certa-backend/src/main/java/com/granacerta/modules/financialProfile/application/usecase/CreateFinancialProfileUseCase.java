package com.granacerta.modules.financialProfile.application.usecase;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.domain.repository.FinancialProfileRepository;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.domain.exception.UserNotFoundException;
import com.granacerta.modules.user.domain.repository.UserRepository;

public class CreateFinancialProfileUseCase {
    private FinancialProfileRepository
     financialProfileRepository;
    private final UserRepository userRepository;

    public CreateFinancialProfileUseCase(FinancialProfileRepository financialProfileRepository, UserRepository userRepository) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }

    public CreateFinancialProfileResult execute(
            CreateFinancialProfileCommand command
    ) {

        User user = userRepository.findByIdAndActiveTrue(command.userId()).orElseThrow(()->new UserNotFoundException(   "User not found"));

        FinancialProfile financialProfile = FinancialProfile.create(
                user, command.objective(),
                command.controlLevel(),
                command.mainDifficulty(),
                command.preferredView(),
                command.monthlyIncome()
        );

        FinancialProfile savedFinancialProfile =
                financialProfileRepository.save(financialProfile);

        return new CreateFinancialProfileResult(

                savedFinancialProfile.getId(),
                savedFinancialProfile.getCreatedAt()


        );
    }
}