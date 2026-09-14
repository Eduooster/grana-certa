package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.exception.TransactionNotFoundException;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;

public class DeleteTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public DeleteTransactionUseCase(
            TransactionRepository transactionRepository
    ) {
        this.transactionRepository = transactionRepository;
    }

    public void execute(
            DeleteTransactionCommand command
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

        transaction.deactivate();
        transactionRepository.save(transaction);    
    }
}
