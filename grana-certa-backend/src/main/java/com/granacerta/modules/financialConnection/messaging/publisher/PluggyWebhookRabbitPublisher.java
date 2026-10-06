package com.granacerta.modules.financialConnection.messaging.publisher;

import com.granacerta.modules.financialConnection.application.gateway.PluggyWebhookPublisher;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PluggyWebhookRabbitPublisher implements PluggyWebhookPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Override
    public void publish(PluggyWebhookCommand command) {

        rabbitTemplate.convertAndSend(
                "pluggy-exchange",
                command.event().getValue(),
                command
        );
    }
}
