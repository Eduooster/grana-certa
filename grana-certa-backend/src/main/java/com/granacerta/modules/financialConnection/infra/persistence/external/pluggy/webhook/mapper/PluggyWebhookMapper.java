package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.mapper;

import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookCommand;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.domain.PluggyWebhookEvent;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.webhook.dto.PluggyWebhookPayload;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PluggyWebhookMapper {
    @Mapping(target = "event", source = "event")


    PluggyWebhookCommand toCommand(PluggyWebhookPayload  request);

    PluggyWebhookCommand.PluggyErrorDetails toErrorDetails(PluggyWebhookPayload.PluggyError error);

    PluggyWebhookCommand.PluggyDataDetails toDataDetails(PluggyWebhookPayload.PluggyData data);

    default PluggyWebhookEvent mapPluggyWebhookEvent(String value) {
        return PluggyWebhookEvent.fromValue(value);
    }

    default UUID mapClientUserId(String clientUserId) {
        return clientUserId != null
                ? UUID.fromString(clientUserId)
                : null;
    }
}
