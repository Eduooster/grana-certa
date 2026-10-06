package com.granacerta.modules.transfer.application.usecase;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import com.granacerta.modules.transfer.domain.entity.Transfer;
import com.granacerta.modules.transfer.domain.repository.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class CreateTransferUseCase {


    private final TransferRepository transferRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final TransactionRepository transactionRepository;

    public CreateTransferUseCase(TransferRepository transferRepository, FinancialAccountRepository financialAccountRepository, TransactionRepository transactionRepository) {
        this.transferRepository = transferRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public CreateTransferResult execute(CreateTransferCommand command) {

        FinancialAccount sourceAccount = financialAccountRepository
                .findByIdAndUserIdAndActiveTrue(command.sourceAccountId(),command.userId(), FinancialAccountStatus.ACTIVE)
                .orElseThrow(() ->
                        new FinancialAccountNotFoundException("Destination account not found")
                );

        FinancialAccount destinationAccount = financialAccountRepository
                .findByIdAndUserIdAndActiveTrue(command.destinationAccountId()  ,command.userId(), FinancialAccountStatus.ACTIVE)
                .orElseThrow(() ->
                        new FinancialAccountNotFoundException("Destination account not found")
                );


        Transfer transfer = Transfer.create(command);

        Transfer savedTransfer = transferRepository.save(transfer);

        Transaction expense = Transaction.createTransferTransaction(
                command.userId(),
                sourceAccount.getId(),
                savedTransfer.getId(),
                TransactionType.EXPENSE,
                command.amount(),
                savedTransfer.getTransferredAt().toLocalDate(),
                "Transferência para " + destinationAccount.getName()
        );


        Transaction income = Transaction.createTransferTransaction(
                command.userId(),
                destinationAccount.getId(),
                savedTransfer.getId(),
                TransactionType.INCOME,
                command.amount(),
                savedTransfer.getTransferredAt().toLocalDate(),
                "Transferência recebida de " + sourceAccount.getName()
        );

        sourceAccount.applyDebit(command.amount());
        destinationAccount.applyCredit(command.amount());
        financialAccountRepository.save(sourceAccount);
        financialAccountRepository.save(destinationAccount);
        transactionRepository.save(expense);
        transactionRepository.save(income);

        return new CreateTransferResult(
                savedTransfer.getId(),
                savedTransfer.getUserId(),
                savedTransfer.getSourceAccountId(),
                savedTransfer.getDestinationAccountId(),
                savedTransfer.getAmount(),
                savedTransfer.getTransferredAt(),
                savedTransfer.getCreatedAt()
        );
    }


}
