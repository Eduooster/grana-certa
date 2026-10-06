package com.granacerta.modules.financialConnection.messaging.consumer;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher.PluggyWebhookDispatcher;
import com.granacerta.modules.financialConnection.messaging.PluggyRabbitConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PluggyWebhookConsumer {

    private final PluggyWebhookDispatcher dispatcher;

    @RabbitListener(queues = PluggyRabbitConfiguration.QUEUE)
    public void consume(PluggyWebhookCommand command) {

        log.info(
                "Pluggy webhook recebido pelo RabbitMQ. Event: {}, ItemId: {}",
                command.event(),
                command.itemId()
        );

       dispatcher.dispatch(command);
    }
}