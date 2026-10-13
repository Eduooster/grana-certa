package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.ItemStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler.ItemStatusHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
@Slf4j
public class ItemStatusDispatcher {

    private final List<ItemStatusHandler> handlers;

    public void dispatch(ExternalFinancialConnection connection) {
        handlers.stream()
                .filter(handler -> handler.supports(connection.financialConnectionStatus()))
                .findFirst()
                .ifPresentOrElse(
                        handler -> handler.handle(connection),
                        () -> log.warn(
                                "Nenhum handler implementado para o status: {}",
                                connection.financialConnectionStatus()
                        )
                );
    }
}
