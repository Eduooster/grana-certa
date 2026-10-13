package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.*;
import com.granacerta.modules.financialConnection.domain.enums.ConnectionExecutionResult;
import com.granacerta.modules.financialConnection.domain.enums.FinancialConnectionStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import org.springframework.stereotype.Component;

@Component
public class PluggyFinancialConnectionMapper {

    public ExternalFinancialConnection toExternalFinancialConnection(
            ItemResponse itemResponse
    ) {
        Connector connector = itemResponse.getConnector();
        Health health = connector.getHealth();

        return new ExternalFinancialConnection(
                itemResponse.getId(),
                itemResponse.getClientUserId(),
                connector.getId().longValue(),
                connector.getName(),
                connector.getImageUrl(),
                health.getStatus().name(),
                connector.getType().getValue(),
                toFinancialConnectionStatus(itemResponse.getStatus()),
                toConnectionExecutionResult(itemResponse.getExecutionStatus()),
                itemResponse.getStatusDetail()
        );
    }

    private FinancialConnectionStatus toFinancialConnectionStatus(
            ItemStatus status
    ) {
        if (status == null) {
            return FinancialConnectionStatus.ERROR;
        }

        return switch (status) {
            case UPDATING, MERGING ->
                    FinancialConnectionStatus.CONNECTING;
            case WAITING_USER_INPUT ->
                    FinancialConnectionStatus.WAITING_USER_INPUT;
            case WAITING_USER_ACTION ->
                    FinancialConnectionStatus.WAITING_USER_ACTION;
            case UPDATED ->
                    FinancialConnectionStatus.CONNECTED;
            case LOGIN_ERROR ->
                    FinancialConnectionStatus.ERROR;
            case OUTDATED ->
                    FinancialConnectionStatus.EXPIRED;
        };
    }

    private ConnectionExecutionResult toConnectionExecutionResult(
            ExecutionStatus status
    ) {
        if (status == null) {
            return ConnectionExecutionResult.ERROR;
        }

        return switch (status) {
            case SUCCESS -> ConnectionExecutionResult.SUCCESS;
            case PARTIAL_SUCCESS -> ConnectionExecutionResult.PARTIAL_SUCCESS;
            case WAITING_USER_INPUT ->
                    ConnectionExecutionResult.WAITING_USER_INPUT;
            case ERROR, CREATE_ERROR ->
                    ConnectionExecutionResult.ERROR;
            default -> ConnectionExecutionResult.ERROR;
        };
    }
}