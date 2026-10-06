package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;


import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;

public interface PluggyEventHandler {


    boolean supports(PluggyWebhookEvent event);


    void handle(PluggyWebhookCommand command);
}