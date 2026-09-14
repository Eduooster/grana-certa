package com.granacerta.modules.transaction.application.usecase;

import com.granacerta.modules.transaction.domain.entity.Transaction;
import com.granacerta.modules.transaction.domain.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class GetTransactionsUseCase {
    private final TransactionRepository transactionRepository;

    public GetTransactionsUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Page<GetTransactionsResult> execute(
            GetTransactionsCommand command
    ) {

        Page<Transaction> transactions =
                transactionRepository.findAllByUserIdAndActiveTrue(
                        command.userId(),
                        PageRequest.of(command.page(), command.size())
                );

        return transactions.map(transaction ->
                new GetTransactionsResult(
                        transaction.getId(),
                        transaction.getAccountId(),
                        transaction.getCategoryId(),
                        transaction.getType(),
                        transaction.getAmount(),
                        transaction.getTransactionDate(),
                        transaction.getDescription(),
                        transaction.getSource()
                )
        );
    }
}
