package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.UpdateConnectionStatusCommand;
import com.granacerta.modules.financialConnection.application.usecase.Item.UpdateConnectionStatusUseCase;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemWaitingUserEventHandler implements PluggyEventHandler{

    private final UpdateConnectionStatusUseCase updateConnectionStatusUseCase;
    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event==PluggyWebhookEvent.ITEM_WAITING_USER_ACTION || event==PluggyWebhookEvent.ITEM_WAITING_USER_INPUT;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

        UpdateConnectionStatusCommand updateConnectionStatusCommand =
                new UpdateConnectionStatusCommand(
                        command.itemId(), FinancialConnectionStatus.CONNECTING
                );
        updateConnectionStatusUseCase.execute(
                updateConnectionStatusCommand
        );


    }
}
