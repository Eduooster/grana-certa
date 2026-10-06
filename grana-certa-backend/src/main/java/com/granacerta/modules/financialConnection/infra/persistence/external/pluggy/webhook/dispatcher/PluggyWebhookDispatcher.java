package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.handler.PluggyEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PluggyWebhookDispatcher {


    private final List<PluggyEventHandler> handlers;

    public void dispatch(PluggyWebhookCommand command) {
        handlers.stream()
                .filter(handler -> handler.supports(command.event()))
                .findFirst()
                .ifPresentOrElse(
                        handler -> handler.handle(command),
                        () -> log.warn("Nenhum handler implementado para o evento: {}", command.event())
                );
    }
}