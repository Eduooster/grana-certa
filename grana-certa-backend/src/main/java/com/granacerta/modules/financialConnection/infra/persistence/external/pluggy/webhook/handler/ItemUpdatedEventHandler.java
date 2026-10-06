package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.ImportFinancialAccountsUseCase;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemUpdatedEventHandler implements PluggyEventHandler {

    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;


    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.ITEM_UPDATED;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

        importFinancialAccountsUseCase.execute(command.itemId());

    }
}
