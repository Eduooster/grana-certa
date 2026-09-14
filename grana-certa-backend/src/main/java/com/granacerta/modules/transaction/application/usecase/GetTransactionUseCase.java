package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.exception.TransactionNotFoundException;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;

public class GetTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public GetTransactionUseCase(
            TransactionRepository transactionRepository
    ) {
        this.transactionRepository = transactionRepository;
    }

    public GetTransactionResult execute(
            GetTransactionCommand command
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

        return new GetTransactionResult(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getCategoryId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getDescription(),
                transaction.getSource()
        );
    }
}