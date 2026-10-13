package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.HandleFinancialConnectionUpdateUseCase;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemUpdatedEventHandler implements PluggyEventHandler {

    private final HandleFinancialConnectionUpdateUseCase handleFinancialConnectionUpdateUseCase;


    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.ITEM_UPDATED;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {
        handleFinancialConnectionUpdateUseCase.execute(command.itemId());


    }
}
