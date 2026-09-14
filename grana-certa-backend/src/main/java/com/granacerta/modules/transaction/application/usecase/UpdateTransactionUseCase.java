package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.exception.CategoryNotFoundException;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.exception.TransactionNotFoundException;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;

public class UpdateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public UpdateTransactionUseCase(
            TransactionRepository transactionRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    public void execute(
            UpdateTransactionCommand command
    ) {
        Transaction transaction = transactionRepository
                .findByIdAndUserIdAndActiveTrue(
                        command.transactionId(),
                        command.userId()
                )
                .orElseThrow(() ->
                        new TransactionNotFoundException(
                                "Transaction not found"
                        )
                );

        if (command.categoryId() != null) {
            validateCategory(command);
        }

        transaction.update(command);

        transactionRepository.save(transaction);
    }

    private void validateCategory(
            UpdateTransactionCommand command
    ) {
        categoryRepository
                .findByIdAndUserIdAndStatus(
                        command.categoryId(),
                        command.userId(),
                        CategoryStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found"
                        )
                );
    }
}