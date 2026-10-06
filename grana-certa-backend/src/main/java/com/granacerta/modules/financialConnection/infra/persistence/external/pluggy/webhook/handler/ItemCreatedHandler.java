package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.ProcessFinancialConnectionCreatedUseCase;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemCreatedHandler implements  PluggyEventHandler {

    private final ProcessFinancialConnectionCreatedUseCase processFinancialConnectionCreatedUseCase;


    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.ITEM_CREATED;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {


        processFinancialConnectionCreatedUseCase.execute(command.itemId());

    }
}
