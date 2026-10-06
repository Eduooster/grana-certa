package com.granacerta.modules.financialConnection.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;


import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluggyRabbitConfiguration {

    public static final String EXCHANGE = "pluggy-exchange";
    public static final String QUEUE = "pluggy-webhook-queue";

    @Bean
    public TopicExchange pluggyExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public org.springframework.amqp.core.Queue pluggyWebhookQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding pluggyWebhookBinding(
            Queue pluggyWebhookQueue,
            TopicExchange pluggyExchange
    ) {
        return BindingBuilder
                .bind(pluggyWebhookQueue)
                .to(pluggyExchange)
                .with("#");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}