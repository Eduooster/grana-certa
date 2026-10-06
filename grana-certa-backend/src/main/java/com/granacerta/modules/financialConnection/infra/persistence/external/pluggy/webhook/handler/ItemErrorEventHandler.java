package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.HandleItemErrorUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Item.UpdateConnectionStatusCommand;
import com.granacerta.modules.financialConnection.application.usecase.Item.UpdateConnectionStatusUseCase;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemErrorEventHandler implements PluggyEventHandler {

    private final UpdateConnectionStatusUseCase updateConnectionStatusUseCase;

    @Override
    public boolean supports(PluggyWebhookEvent event) {
        return event == PluggyWebhookEvent.ITEM_ERROR;
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

        UpdateConnectionStatusCommand updateConnectionStatusCommand =
                new UpdateConnectionStatusCommand(
                        command.itemId(),FinancialConnectionStatus.ERROR
                );
        updateConnectionStatusUseCase.execute(
               updateConnectionStatusCommand
        );

    }
}
