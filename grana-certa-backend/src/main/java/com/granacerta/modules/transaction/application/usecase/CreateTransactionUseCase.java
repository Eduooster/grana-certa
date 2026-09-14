package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.exception.CategoryNotFoundException;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;

import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Slf4j
public class CreateTransactionUseCase {





    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository;
    private final FinancialAccountRepository financialAccountRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository, CategoryRepository categoryRepository, FinancialAccountRepository financialAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    public CreateTransactionResult execute(CreateTransactionCommand command) {

        validateFinancialAccount(command);

        if (command.categoryId() != null) {
            validateCategory(command);
        }

       Transaction transaction = Transaction.createManualTransaction(command);

        log.info("is active? create " + transaction.isActive());

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        log.info("saved active?  " + savedTransaction.isActive());

        return new CreateTransactionResult(
                savedTransaction.getId(),
                savedTransaction.getUserId(),
                savedTransaction.getAccountId(),
                savedTransaction.getCategoryId(),
                savedTransaction.getType(),
                savedTransaction.getAmount(),
                savedTransaction.getTransactionDate(),
                savedTransaction.getDescription(),
                savedTransaction.getSource(),
                savedTransaction.getCreatedAt()
        );
    }
    private void validateFinancialAccount(CreateTransactionCommand command) {
        financialAccountRepository
                .findByIdAndUserIdAndActiveTrue(
                        command.accountId(),
                        command.userId(),
                        FinancialAccountStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new FinancialAccountNotFoundException("Account not found")
                );
    }

    private void validateCategory(CreateTransactionCommand command) {
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
}
