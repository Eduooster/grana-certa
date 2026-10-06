package com.granacerta.unit.application;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import com.granacerta.modules.transfer.application.usecase.CreateTransferCommand;
import com.granacerta.modules.transfer.application.usecase.CreateTransferResult;
import com.granacerta.modules.transfer.application.usecase.CreateTransferUseCase;
import com.granacerta.modules.transfer.domain.entity.Transfer;
import com.granacerta.modules.transfer.domain.repository.TransferRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class CreateTransferUseCaseTest {

    @Mock
    private FinancialAccountRepository financialAccountRepository;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private FinancialAccount sourceAccount;

    @Mock
    private FinancialAccount destinationAccount;

    @Mock
    private Transfer transfer;

    @Mock
    private Transfer savedTransfer;

    @Mock
    private Transaction expense;

    @Mock
    private Transaction income;

    private CreateTransferUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new CreateTransferUseCase(
                transferRepository,
                financialAccountRepository  ,
                transactionRepository
        );
    }

    @Test
    void shouldCreateTransferSuccessfully() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();
        UUID transferId = UUID.randomUUID();

        BigDecimal amount = new BigDecimal("500.00");

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                amount
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(sourceAccount));

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                destinationAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(destinationAccount));

        when(sourceAccount.getId()).thenReturn(sourceAccountId);
        when(destinationAccount.getId()).thenReturn(destinationAccountId);

        when(sourceAccount.getName()).thenReturn("Conta Principal");
        when(destinationAccount.getName()).thenReturn("Poupança");

        try (MockedStatic<Transfer> mockedTransfer =
                     Mockito.mockStatic(Transfer.class);

             MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransfer
                    .when(() -> Transfer.create(command))
                    .thenReturn(transfer);

            when(transferRepository.save(transfer))
                    .thenReturn(savedTransfer);

            when(savedTransfer.getId()).thenReturn(transferId);
            when(savedTransfer.getUserId()).thenReturn(userId);
            when(savedTransfer.getSourceAccountId()).thenReturn(sourceAccountId);
            when(savedTransfer.getDestinationAccountId()).thenReturn(destinationAccountId);
            when(savedTransfer.getAmount()).thenReturn(amount);
            when(savedTransfer.getTransferredAt())
                    .thenReturn(LocalDateTime.of(2026, 9, 22, 10, 0));
            when(savedTransfer.getCreatedAt())
                    .thenReturn(LocalDateTime.of(2026, 9, 22, 10, 0));

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            sourceAccountId,
                            transferId,
                            TransactionType.EXPENSE,
                            amount,
                            savedTransfer.getTransferredAt().toLocalDate(),
                            "Transferência para " + destinationAccount.getName()
                    ))
                    .thenReturn(expense);

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            destinationAccountId,
                            transferId,
                            TransactionType.INCOME,
                            amount,
                            savedTransfer.getTransferredAt().toLocalDate(),
                            "Transferência recebida de " + sourceAccount.getName()
                    ))
                    .thenReturn(income);

            when(transactionRepository.save(expense))
                    .thenReturn(expense);

            when(transactionRepository.save(income))
                    .thenReturn(income);

            CreateTransferResult result = useCase.execute(command);

            assertNotNull(result);
            assertEquals(transferId, result.id());
            assertEquals(userId, result.userId());
            assertEquals(sourceAccountId, result.sourceAccountId());
            assertEquals(destinationAccountId, result.destinationAccountId());
            assertEquals(amount, result.amount());
            assertEquals(
                    savedTransfer.getTransferredAt(),
                    result.transferredAt()
            );
            assertEquals(
                    savedTransfer.getCreatedAt(),
                    result.createdAt()
            );

            verify(transferRepository).save(transfer);

            verify(sourceAccount).applyDebit(amount);
            verify(destinationAccount).applyCredit(amount);

            verify(financialAccountRepository).save(sourceAccount);
            verify(financialAccountRepository).save(destinationAccount);

            verify(transactionRepository).save(expense);
            verify(transactionRepository).save(income);
        }
    }

    @Test
    void shouldThrowWhenSourceAccountDoesNotExist() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                new BigDecimal("500.00")
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.empty());

        assertThrows(
                FinancialAccountNotFoundException.class,
                () -> useCase.execute(command)
        );

        verify(financialAccountRepository, never())
                .findByIdAndUserIdAndActiveTrue(
                        destinationAccountId,
                        userId,
                        FinancialAccountStatus.ACTIVE
                );

        verify(transferRepository, never()).save(any());
        verify(transactionRepository, never()).save(any());
        verify(sourceAccount, never()).applyDebit(any());
        verify(destinationAccount, never()).applyCredit(any());
    }

    @Test
    void shouldThrowWhenDestinationAccountDoesNotExist() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                new BigDecimal("500.00")
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(sourceAccount));

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                destinationAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.empty());

        assertThrows(
                FinancialAccountNotFoundException.class,
                () -> useCase.execute(command)
        );

        verify(transferRepository, never()).save(any());
        verify(transactionRepository, never()).save(any());

        verify(sourceAccount, never()).applyDebit(any());
        verify(destinationAccount, never()).applyCredit(any());

        verify(financialAccountRepository, never())
                .save(any(FinancialAccount.class));
    }

    @Test
    void shouldCreateTransferBeforeCreatingTransactions() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();
        UUID transferId = UUID.randomUUID();

        BigDecimal amount = new BigDecimal("300.00");

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                amount
        );

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(sourceAccount));

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                destinationAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(destinationAccount));

        when(sourceAccount.getId()).thenReturn(sourceAccountId);
        when(destinationAccount.getId()).thenReturn(destinationAccountId);
        when(sourceAccount.getName()).thenReturn("Conta A");
        when(destinationAccount.getName()).thenReturn("Conta B");

        try (MockedStatic<Transfer> mockedTransfer =
                     Mockito.mockStatic(Transfer.class);

             MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransfer
                    .when(() -> Transfer.create(command))
                    .thenReturn(transfer);

            when(transferRepository.save(transfer))
                    .thenReturn(savedTransfer);

            when(savedTransfer.getId()).thenReturn(transferId);
            when(savedTransfer.getTransferredAt())
                    .thenReturn(LocalDateTime.of(2026, 9, 22, 10, 0));

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            any(),
                            any(),
                            any(),
                            any(),
                            any(),
                            any(),
                            any()
                    ))
                    .thenReturn(expense, income);

            InOrder inOrder = inOrder(
                    transferRepository,
                    sourceAccount,
                    destinationAccount,
                    financialAccountRepository,
                    transactionRepository
            );

            useCase.execute(command);

            inOrder.verify(transferRepository).save(transfer);

            inOrder.verify(sourceAccount).applyDebit(amount);
            inOrder.verify(destinationAccount).applyCredit(amount);

            inOrder.verify(financialAccountRepository).save(sourceAccount);
            inOrder.verify(financialAccountRepository).save(destinationAccount);

            inOrder.verify(transactionRepository).save(expense);
            inOrder.verify(transactionRepository).save(income);
        }
    }

    @Test
    void shouldCreateExpenseTransactionForSourceAccount() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();
        UUID transferId = UUID.randomUUID();

        BigDecimal amount = new BigDecimal("200.00");

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                amount
        );

        LocalDateTime transferredAt =
                LocalDateTime.of(2026, 9, 22, 10, 0);

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(sourceAccount));

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                destinationAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(destinationAccount));

        when(sourceAccount.getId()).thenReturn(sourceAccountId);
        when(destinationAccount.getId()).thenReturn(destinationAccountId);
        when(sourceAccount.getName()).thenReturn("Conta A");
        when(destinationAccount.getName()).thenReturn("Conta B");

        try (MockedStatic<Transfer> mockedTransfer =
                     Mockito.mockStatic(Transfer.class);

             MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransfer
                    .when(() -> Transfer.create(command))
                    .thenReturn(transfer);

            when(transferRepository.save(transfer))
                    .thenReturn(savedTransfer);

            when(savedTransfer.getId()).thenReturn(transferId);
            when(savedTransfer.getTransferredAt())
                    .thenReturn(transferredAt);

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            sourceAccountId,
                            transferId,
                            TransactionType.EXPENSE,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência para Conta B"
                    ))
                    .thenReturn(expense);

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            destinationAccountId,
                            transferId,
                            TransactionType.INCOME,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência recebida de Conta A"
                    ))
                    .thenReturn(income);

            useCase.execute(command);

            mockedTransaction.verify(() ->
                    Transaction.createTransferTransaction(
                            userId,
                            sourceAccountId,
                            transferId,
                            TransactionType.EXPENSE,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência para Conta B"
                    )
            );
        }
    }

    @Test
    void shouldCreateIncomeTransactionForDestinationAccount() {

        UUID userId = UUID.randomUUID();
        UUID sourceAccountId = UUID.randomUUID();
        UUID destinationAccountId = UUID.randomUUID();
        UUID transferId = UUID.randomUUID();

        BigDecimal amount = new BigDecimal("200.00");

        CreateTransferCommand command = new CreateTransferCommand(
                userId,
                sourceAccountId,
                destinationAccountId,
                amount
        );

        LocalDateTime transferredAt =
                LocalDateTime.of(2026, 9, 22, 10, 0);

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                sourceAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(sourceAccount));

        when(financialAccountRepository.findByIdAndUserIdAndActiveTrue(
                destinationAccountId,
                userId,
                FinancialAccountStatus.ACTIVE
        )).thenReturn(Optional.of(destinationAccount));

        when(sourceAccount.getId()).thenReturn(sourceAccountId);
        when(destinationAccount.getId()).thenReturn(destinationAccountId);
        when(sourceAccount.getName()).thenReturn("Conta A");
        when(destinationAccount.getName()).thenReturn("Conta B");

        try (MockedStatic<Transfer> mockedTransfer =
                     Mockito.mockStatic(Transfer.class);

             MockedStatic<Transaction> mockedTransaction =
                     Mockito.mockStatic(Transaction.class)) {

            mockedTransfer
                    .when(() -> Transfer.create(command))
                    .thenReturn(transfer);

            when(transferRepository.save(transfer))
                    .thenReturn(savedTransfer);

            when(savedTransfer.getId()).thenReturn(transferId);
            when(savedTransfer.getTransferredAt())
                    .thenReturn(transferredAt);

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            sourceAccountId,
                            transferId,
                            TransactionType.EXPENSE,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência para Conta B"
                    ))
                    .thenReturn(expense);

            mockedTransaction
                    .when(() -> Transaction.createTransferTransaction(
                            userId,
                            destinationAccountId,
                            transferId,
                            TransactionType.INCOME,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência recebida de Conta A"
                    ))
                    .thenReturn(income);

            useCase.execute(command);

            mockedTransaction.verify(() ->
                    Transaction.createTransferTransaction(
                            userId,
                            destinationAccountId,
                            transferId,
                            TransactionType.INCOME,
                            amount,
                            transferredAt.toLocalDate(),
                            "Transferência recebida de Conta A"
                    )
            );
        }
    }
}