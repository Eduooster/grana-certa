package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.SyncConnection.UpdateConnectionStatusCommand;
import com.granacerta.modules.financialConnection.application.usecase.SyncConnection.UpdateConnectionStatusUseCase;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
        //Os eventos item/waiting_user_input e item/waiting_user_action indicam que a conexão com a instituição financeira ainda não foi completada e, portanto, não há um itemId criado até esse ponto. Isso significa que:

        //sugestão: disparar notificação informando o usuario que falta alguma ação

        log.info("Handling ItemWaitingUserEvent - sending a notificationFFF");

    }
}
