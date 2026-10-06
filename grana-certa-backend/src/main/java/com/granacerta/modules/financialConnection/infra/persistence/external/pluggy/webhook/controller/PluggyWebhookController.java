package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.controller;

import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.application.gateway.PluggyWebhookPublisher;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dto.PluggyWebhookPayload;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.mapper.PluggyWebhookMapper;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dispatcher.PluggyWebhookDispatcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/webhooks/pluggy")
@RequiredArgsConstructor
@Slf4j
public class PluggyWebhookController {

    private final PluggyWebhookDispatcher dispatcher;
    @Qualifier("pluggyWebhookMapper")
    private final PluggyWebhookMapper mapper;
    private final PluggyWebhookPublisher publisher;


    @PostMapping
    public ResponseEntity<Void> handle(
            @RequestBody PluggyWebhookPayload request
    ) {

            PluggyWebhookCommand command = mapper.toCommand(request);
            publisher.publish(command);

            return ResponseEntity.ok(   ).build();

    }


}
