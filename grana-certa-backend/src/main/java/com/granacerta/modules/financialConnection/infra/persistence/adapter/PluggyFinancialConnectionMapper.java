package com.granacerta.modules.financialConnection.infra.persistence.adapter;

import ai.pluggy.client.response.Connector;
import ai.pluggy.client.response.ItemResponse;
import ai.pluggy.client.response.ItemStatus;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import org.springframework.stereotype.Component;

@Component
public class PluggyFinancialConnectionMapper {


        public ExternalFinancialConnection toExternalFinancialConnection(
                ItemResponse itemResponse
        ) {
            Connector connector = itemResponse.getConnector();
            ItemStatus status = itemResponse.getStatus();

            return new ExternalFinancialConnection(
                    itemResponse.getId(),
                    itemResponse.getClientUserId(),
                    connector.getId().longValue(),
                    connector.getName(),
                    connector.getImageUrl(),
                    "TESTE",
                    connector.getType().getValue(),
                    status
            );
        }
    }

