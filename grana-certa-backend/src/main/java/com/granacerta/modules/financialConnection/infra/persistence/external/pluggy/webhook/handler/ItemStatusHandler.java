package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler;

import ai.pluggy.client.response.ItemStatus;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;

public interface ItemStatusHandler {

    boolean supports(FinancialConnectionStatus status);


    void handle(ExternalFinancialConnection connection);


}
