package com.granacerta.modules.financialConnection.application.gateway;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;

public interface PluggyWebhookPublisher  {
    void publish(PluggyWebhookCommand command);
}
