package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.ImportTransactionsUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Item.ProcessTransactionCreatedUseCase;
import com.granacerta.modules.financialConnection.application.usecase.ProcessTransactionCreatedCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Slf4j
@Component
@RequiredArgsConstructor
public class TranscationsCreatedHandler implements PluggyEventHandler{

    private final ProcessTransactionCreatedUseCase processTransactionCreatedUseCase;
    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.TRANSACTIONS_CREATED;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

        log.info("Handling transactions created");
        ProcessTransactionCreatedCommand processTransactionCreatedCommand = new ProcessTransactionCreatedCommand(
                command.itemId(), command.accountId(),command.createdTransactionsLink()
        );
        processTransactionCreatedUseCase.execute(processTransactionCreatedCommand);

    }
}
