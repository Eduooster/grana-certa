package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dto;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluggyWebhookError(
        String code,
        String message
) {}