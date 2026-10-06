package com.granacerta.modules.transaction.orchestrator;

import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceCommand;
import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceResult;
import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceUseCase;
import com.granacerta.modules.transaction.application.usecase.CreateTransactionCommand;
import com.granacerta.modules.transaction.application.usecase.CreateTransactionResult;
import com.granacerta.modules.transaction.application.usecase.CreateTransactionUseCase;
import com.granacerta.modules.transaction.web.dto.CreateTransactionRequest;

import java.util.UUID;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
public class CreateTransactionOrchestrator {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final CreateRecurrenceUseCase createRecurrenceUseCase;

    public CreateTransactionOrchestrator(
            CreateTransactionUseCase createTransactionUseCase,
            CreateRecurrenceUseCase createRecurrenceUseCase
    ) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.createRecurrenceUseCase = createRecurrenceUseCase;
    }

    @Transactional
    public CreateTransactionResult execute(CreateTransactionOperationCommand command) {
        if (command.recurrence() == null) {
            return createTransactionUseCase.execute(toTransactionCommand(command, null));
        }

        CreateRecurrenceResult recurrenceResult = createRecurrenceUseCase.execute(toRecurrenceCommand(command));

        log.info("result Id" + recurrenceResult.id());


        return createTransactionUseCase.execute(toTransactionCommand(command, recurrenceResult.id()));
    }

    private CreateTransactionCommand toTransactionCommand(
            CreateTransactionOperationCommand command,
            UUID recurrenceId
    ) {
        return new CreateTransactionCommand(
                command.userId(),
                command.accountId(),
                command.categoryId(),
                command.type(),
                command.amount(),
                command.transactionDate(),
                command.description(),
                recurrenceId
        );
    }

    private CreateRecurrenceCommand toRecurrenceCommand(CreateTransactionOperationCommand command) {
        return new CreateRecurrenceCommand(
                command.userId(),
                command.accountId(),
                command.categoryId(),
                command.type(),
                command.amount(),
                command.description(),
                command.recurrence().frequency(),
                command.recurrence().intervalValue(),
                command.recurrence().startDate(),
                command.recurrence().endDate()
        );
    }
}