package com.granacerta.unit.application;


import com.granacerta.modules.category.domain.entity.Category;
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
import com.granacerta.modules.transaction.application.usecase.CreateTransactionCommand;
import com.granacerta.modules.transaction.application.usecase.CreateTransactionResult;
import com.granacerta.modules.transaction.application.usecase.CreateTransactionUseCase;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.enums.TransactionSource;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private FinancialAccountRepository financialAccountRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private InvoiceResolver invoiceResolver;

    @Mock
    private FinancialAccount account;

    @Mock
    private Transaction transaction;

    @Mock
    private Invoice invoice;

    private CreateTransactionUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateTransactionUseCase(
                financialAccountRepository,
                categoryRepository,
                transactionRepository,
                invoiceResolver
        );
    }

    @Test
    void shouldCreateTransactionSuccessfullyWithoutCategory() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID transactionId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.EXPENSE,
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Supermercado",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.DEBIT);

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createManualTransaction(command))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            when(transaction.getId()).thenReturn(transactionId);
            when(transaction.getUserId()).thenReturn(userId);
            when(transaction.getAccountId()).thenReturn(accountId);
            when(transaction.getCategoryId()).thenReturn(null);
            when(transaction.getType()).thenReturn(TransactionType.EXPENSE);
            when(transaction.getAmount()).thenReturn(new BigDecimal("100.00"));
            when(transaction.getTransactionDate()).thenReturn(command.transactionDate());
            when(transaction.getDescription()).thenReturn("Supermercado");
            when(transaction.getSource()).thenReturn(TransactionSource.MANUAL);
            when(transaction.getCreatedAt()).thenReturn(LocalDateTime.now());

            CreateTransactionResult result = useCase.execute(command);

            assertNotNull(result);
            assertEquals(transactionId, result.id());
            assertEquals(userId, result.userId());
            assertEquals(accountId, result.accountId());
            assertNull(result.categoryId());
            assertEquals(TransactionType.EXPENSE, result.type());
            assertEquals(new BigDecimal("100.00"), result.amount());
            assertEquals(TransactionSource.MANUAL, result.source());

            verify(account).applyTransaction(transaction);
            verify(transactionRepository).save(transaction);
            verify(financialAccountRepository).save(account);

            verify(categoryRepository, never())
                    .findByIdAndUserIdAndStatus(
                            any(),
                            any(),
                            any()
                    );

            verifyNoInteractions(invoiceResolver);
        }
    }

    @Test
    void shouldCreateTransactionSuccessfullyWithCategory() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                categoryId,
                TransactionType.EXPENSE,
                new BigDecimal("150.00"),
                LocalDate.now(),
                "Restaurante",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.DEBIT);

        when(categoryRepository.findByIdAndUserIdAndStatus(
                categoryId,
                userId,
                CategoryStatus.ACTIVE
        )).thenReturn(Optional.of(mock(Category.class)));

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createManualTransaction(command))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            CreateTransactionResult result = useCase.execute(command);

            assertNotNull(result);

            verify(categoryRepository).findByIdAndUserIdAndStatus(
                    categoryId,
                    userId,
                    CategoryStatus.ACTIVE
            );

            verify(account).applyTransaction(transaction);
            verify(transactionRepository).save(transaction);
            verify(financialAccountRepository).save(account);

            verifyNoInteractions(invoiceResolver);
        }
    }

    @Test
    void shouldCreateRecurrenceTransaction() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();
        UUID recurrenceId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                categoryId,
                TransactionType.EXPENSE,
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Netflix",
                recurrenceId
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.DEBIT);

        when(categoryRepository.findByIdAndUserIdAndStatus(
                categoryId,
                userId,
                CategoryStatus.ACTIVE
        )).thenReturn(Optional.of(mock(Category.class)));

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createRecurrenceTransaction(command))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            useCase.execute(command);

            mockedTransaction.verify(
                    () -> Transaction.createRecurrenceTransaction(command)
            );

            mockedTransaction.verify(
                    () -> Transaction.createManualTransaction(command),
                    never()
            );

            verify(account).applyTransaction(transaction);
            verify(transactionRepository).save(transaction);
            verify(financialAccountRepository).save(account);

            verifyNoInteractions(invoiceResolver);
        }
    }

    @Test
    void shouldCreateCreditCardTransactionWithInvoice() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();
        UUID invoiceId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                categoryId,
                TransactionType.EXPENSE,
                new BigDecimal("300.00"),
                LocalDate.of(2026, 9, 27),
                "Compra cartão",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.CREDIT_CARD);

        when(categoryRepository.findByIdAndUserIdAndStatus(
                categoryId,
                userId,
                CategoryStatus.ACTIVE
        )).thenReturn(Optional.of(mock(Category.class)));

        when(invoiceResolver.resolve(
                account,
                command.transactionDate()
        )).thenReturn(invoice);

        when(invoice.getId())
                .thenReturn(invoiceId);

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createInvoiceTransaction(
                            userId,
                            accountId,
                            categoryId,
                            TransactionType.EXPENSE,
                            new BigDecimal("300.00"),
                            command.transactionDate(),
                            "Compra cartão",
                            invoiceId
                    ))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            useCase.execute(command);

            verify(invoiceResolver).resolve(
                    account,
                    command.transactionDate()
            );

            mockedTransaction.verify(
                    () -> Transaction.createInvoiceTransaction(
                            userId,
                            accountId,
                            categoryId,
                            TransactionType.EXPENSE,
                            new BigDecimal("300.00"),
                            command.transactionDate(),
                            "Compra cartão",
                            invoiceId
                    )
            );

            mockedTransaction.verify(
                    () -> Transaction.createManualTransaction(command),
                    never()
            );

            mockedTransaction.verify(
                    () -> Transaction.createRecurrenceTransaction(command),
                    never()
            );

            verify(account).applyTransaction(transaction);
            verify(transactionRepository).save(transaction);
            verify(financialAccountRepository).save(account);
        }
    }

    @Test
    void shouldUseCreditCardFlowEvenWhenRecurrenceIsProvided() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID recurrenceId = UUID.randomUUID();
        UUID invoiceId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.EXPENSE,
                new BigDecimal("200.00"),
                LocalDate.now(),
                "Compra parcelada",
                recurrenceId
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.CREDIT_CARD);

        when(invoiceResolver.resolve(
                account,
                command.transactionDate()
        )).thenReturn(invoice);

        when(invoice.getId())
                .thenReturn(invoiceId);

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createInvoiceTransaction(
                            userId,
                            accountId,
                            null,
                            TransactionType.EXPENSE,
                            new BigDecimal("200.00"),
                            command.transactionDate(),
                            "Compra parcelada",
                            invoiceId
                    ))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            useCase.execute(command);

            verify(invoiceResolver).resolve(
                    account,
                    command.transactionDate()
            );

            mockedTransaction.verify(
                    () -> Transaction.createInvoiceTransaction(
                            userId,
                            accountId,
                            null,
                            TransactionType.EXPENSE,
                            new BigDecimal("200.00"),
                            command.transactionDate(),
                            "Compra parcelada",
                            invoiceId
                    )
            );

            mockedTransaction.verify(
                    () -> Transaction.createRecurrenceTransaction(command),
                    never()
            );

            verify(account).applyTransaction(transaction);
            verify(transactionRepository).save(transaction);
            verify(financialAccountRepository).save(account);
        }
    }

    @Test
    void shouldThrowWhenFinancialAccountDoesNotExist() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.EXPENSE,
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Teste",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.empty());

        assertThrows(
                FinancialAccountNotFoundException.class,
                () -> useCase.execute(command)
        );

        verify(transactionRepository, never()).save(any());
        verify(financialAccountRepository, never()).save(any());

        verify(categoryRepository, never())
                .findByIdAndUserIdAndStatus(any(), any(), any());

        verifyNoInteractions(invoiceResolver);
    }

    @Test
    void shouldThrowWhenCategoryDoesNotExist() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                categoryId,
                TransactionType.EXPENSE,
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Teste",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(categoryRepository.findByIdAndUserIdAndStatus(
                categoryId,
                userId,
                CategoryStatus.ACTIVE
        )).thenReturn(Optional.empty());

        assertThrows(
                CategoryNotFoundException.class,
                () -> useCase.execute(command)
        );

        verify(categoryRepository).findByIdAndUserIdAndStatus(
                categoryId,
                userId,
                CategoryStatus.ACTIVE
        );

        verify(account, never()).applyTransaction(any());
        verify(transactionRepository, never()).save(any());
        verify(financialAccountRepository, never()).save(any());

        verifyNoInteractions(invoiceResolver);
    }

    @Test
    void shouldApplyTransactionToFinancialAccountBeforeSaving() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.INCOME,
                new BigDecimal("2000.00"),
                LocalDate.now(),
                "Salário",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.DEBIT);

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createManualTransaction(command))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            useCase.execute(command);

            InOrder inOrder = inOrder(
                    account,
                    transactionRepository,
                    financialAccountRepository
            );

            inOrder.verify(account)
                    .applyTransaction(transaction);

            inOrder.verify(transactionRepository)
                    .save(transaction);

            inOrder.verify(financialAccountRepository)
                    .save(account);
        }
    }

    @Test
    void shouldCreateManualTransaction() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.EXPENSE,
                new BigDecimal("50.00"),
                LocalDate.now(),
                "Café",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.DEBIT);

        try (MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransaction
                    .when(() -> Transaction.createManualTransaction(command))
                    .thenReturn(transaction);

            when(transactionRepository.save(transaction))
                    .thenReturn(transaction);

            useCase.execute(command);

            mockedTransaction.verify(
                    () -> Transaction.createManualTransaction(command)
            );
        }
    }

    @Test
    void shouldNotSaveAnythingWhenInvoiceResolutionFails() {

        UUID userId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();

        CreateTransactionCommand command = new CreateTransactionCommand(
                userId,
                accountId,
                null,
                TransactionType.EXPENSE,
                new BigDecimal("100.00"),
                LocalDate.now(),
                "Compra",
                null
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                accountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(account));

        when(account.getType())
                .thenReturn(FinancialAccountType.CREDIT_CARD);

        RuntimeException exception =
                new RuntimeException("Invoice could not be resolved");

        when(invoiceResolver.resolve(
                account,
                command.transactionDate()
        )).thenThrow(exception);

        assertThrows(
                RuntimeException.class,
                () -> useCase.execute(command)
        );

        verify(account, never()).applyTransaction(any());
        verify(transactionRepository, never()).save(any());
        verify(financialAccountRepository, never()).save(any());
    }
}

