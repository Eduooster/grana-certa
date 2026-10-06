package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.category.domain.enums.CategoryStatus;
import com.granacerta.modules.category.domain.exception.CategoryNotFoundException;
import com.granacerta.modules.category.domain.repository.CategoryRepository;
import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;

import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.domain.resolve.InvoiceResolver;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;





@Transactional
public class CreateTransactionUseCase {

    private final FinancialAccountRepository financialAccountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final InvoiceResolver invoiceResolver;

    public CreateTransactionUseCase(FinancialAccountRepository financialAccountRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository, InvoiceResolver invoiceResolver) {
        this.financialAccountRepository = financialAccountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
        this.invoiceResolver = invoiceResolver;
    }

    public CreateTransactionResult execute(CreateTransactionCommand command) {

        FinancialAccount account = validateFinancialAccount(command);

        if (command.categoryId() != null) {
            validateCategory(command);
        }


        Transaction transaction = createTransaction(account, command);


        account.applyTransaction(transaction);

        Transaction savedTransaction = transactionRepository.save(transaction);
        financialAccountRepository.save(account);


        return buildResult(savedTransaction);
    }



    private Transaction createTransaction(FinancialAccount account, CreateTransactionCommand command) {
        if (FinancialAccountType.CREDIT.equals(account.getType())) {
            return createCreditCardTransaction(account, command);
        }

        if (command.recurrenceId() != null) {
            return Transaction.createRecurrenceTransaction(command);
        }

        return Transaction.createManualTransaction(command);
    }

    private Transaction createCreditCardTransaction(FinancialAccount account, CreateTransactionCommand command) {
        Invoice invoice = invoiceResolver.resolve(
                account,
                command.transactionDate()
        );

        return Transaction.createInvoiceTransaction(
                command.userId(),
                command.accountId(),
                command.categoryId(),
                command.type(),
                command.amount(),
                command.transactionDate(),
                command.description(),
                invoice.getId()
        );
    }

    private CreateTransactionResult buildResult(Transaction transaction) {
        return new CreateTransactionResult(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAccountId(),
                transaction.getCategoryId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getDescription(),
                transaction.getSource(),
                transaction.getCreatedAt()
        );
    }



    private FinancialAccount validateFinancialAccount(
            CreateTransactionCommand command
    ) {
        return financialAccountRepository
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