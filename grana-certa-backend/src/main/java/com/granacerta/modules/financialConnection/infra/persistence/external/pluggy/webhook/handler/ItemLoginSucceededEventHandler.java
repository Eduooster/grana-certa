package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Process.ProcessFinancialConnectionCreatedUseCase;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemLoginSucceededEventHandler implements PluggyEventHandler{

    private final ProcessFinancialConnectionCreatedUseCase processFinancialConnectionCreatedUseCase;



    @Override
    public boolean supports(PluggyWebhookEvent event) {
        {
            return event == PluggyWebhookEvent.LOGIN_SUCCESS;}
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

       processFinancialConnectionCreatedUseCase.execute(command.itemId());

    }
}
