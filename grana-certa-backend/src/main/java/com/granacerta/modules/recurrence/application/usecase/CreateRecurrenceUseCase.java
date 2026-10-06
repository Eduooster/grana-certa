package com.granacerta.modules.recurrence.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.exception.CategoryNotFoundException;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.recurrence.domain.entity.Recurrence;
import com.granacerta.modules.recurrence.domain.repository.RecurrenceRepository;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CreateRecurrenceUseCase {


    private final FinancialAccountRepository financialAccountRepository;
    private final CategoryRepository categoryRepository;


    private final RecurrenceRepository recurrenceRepository;

    public CreateRecurrenceUseCase(FinancialAccountRepository financialAccountRepository, CategoryRepository categoryRepository, RecurrenceRepository recurrenceRepository) {
        this.financialAccountRepository = financialAccountRepository;
        this.categoryRepository = categoryRepository;

        this.recurrenceRepository = recurrenceRepository;
    }

    @Transactional

    public CreateRecurrenceResult execute(CreateRecurrenceCommand command) {

        FinancialAccount account = financialAccountRepository
                .findByIdAndUserIdAndActiveTrue(
                        command.accountId(),
                        command.userId(),
                        FinancialAccountStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new FinancialAccountNotFoundException("Account not found")
                );

        if (command.categoryId() != null) {
            categoryRepository
                    .findByIdAndUserIdAndStatus(
                            command.categoryId(),
                            command.userId(),
                            CategoryStatus.ACTIVE
                    )
                    .orElseThrow(() ->
                            new CategoryNotFoundException("Category not found")
                    );
        }

        Recurrence recurrence = Recurrence.create(command);

        Recurrence savedRecurrence = recurrenceRepository.save(recurrence);


        log.info("Created recurrence: {}", savedRecurrence.getId());




        financialAccountRepository.save(account);

        return new CreateRecurrenceResult(
                savedRecurrence.getId(),
                savedRecurrence.getUserId(),
                savedRecurrence.getAccountId(),
                savedRecurrence.getCategoryId(),
                savedRecurrence.getType(),
                savedRecurrence.getAmount(),
                savedRecurrence.getStartDate(),
                savedRecurrence.getEndDate(),
                savedRecurrence.getFrequency(),
                savedRecurrence.getIntervalValue(),
                savedRecurrence.getDescription(),
                savedRecurrence.getStatus(),
                savedRecurrence.getCreatedAt());

    }
}