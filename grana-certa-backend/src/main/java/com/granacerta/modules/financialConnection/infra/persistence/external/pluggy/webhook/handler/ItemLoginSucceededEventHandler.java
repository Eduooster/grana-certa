package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import com.granacerta.modules.financialConnection.application.usecase.Item.ProcessFinancialConnectionLoginSuccessUseCase;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class ItemLoginSucceededEventHandler implements PluggyEventHandler{

    private final ProcessFinancialConnectionLoginSuccessUseCase processFinancialConnectionLoginSuccessUseCase;

    public ItemLoginSucceededEventHandler(ProcessFinancialConnectionLoginSuccessUseCase processFinancialConnectionLoginSuccessUseCase) {
        this.processFinancialConnectionLoginSuccessUseCase = processFinancialConnectionLoginSuccessUseCase;
    }

    @Override
    public boolean supports(PluggyWebhookEvent event) {
        {
            return event == PluggyWebhookEvent.LOGIN_SUCCESS;}
    }

    @Override
    public void handle(PluggyWebhookCommand command) {

        log.info("handle ItemLoginSucceeded");
        log.info("client user: {}", command.clientUserId());

        processFinancialConnectionLoginSuccessUseCase.execute(command.itemId());

    }
}
