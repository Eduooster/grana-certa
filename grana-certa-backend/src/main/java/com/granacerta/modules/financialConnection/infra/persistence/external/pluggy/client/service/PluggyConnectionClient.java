package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service;

import ai.pluggy.client.PluggyClient;
import ai.pluggy.client.response.ConnectTokenResponse;
import ai.pluggy.client.response.ErrorResponse;import ai.pluggy.client.request.CreateConnectTokenRequest;
import ai.pluggy.client.response.ItemResponse;
import com.granacerta.modules.financialConnection.application.gateway.FinancialConnectionProviderGateway;
import com.granacerta.modules.financialConnection.infra.persistence.adapter.PluggyFinancialConnectionMapper;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.util.*;

@Component
public class PluggyConnectionClient implements FinancialConnectionProviderGateway {

    private final PluggyClient pluggyClient;
    private static final String clientId=  System.getenv("PLUGGY_CLIENT_ID");
    private static final String clientSecret=  System.getenv("PLUGGY_CLIENT_SECRET");
    private final PluggyFinancialConnectionMapper pluggyFinancialConnectionMapper;

    public PluggyConnectionClient(PluggyFinancialConnectionMapper pluggyFinancialConnectionMapper) {
        this.pluggyFinancialConnectionMapper = pluggyFinancialConnectionMapper;
        if (clientId == null || clientSecret == null) {
            throw new IllegalStateException("Environment variables not set");
        }

        this.pluggyClient = PluggyClient.builder()
                .clientIdAndSecret(clientId, clientSecret)
                .build();

    }



    @Override
    public String generateConnectionToken(UUID userId) {

        Objects.requireNonNull(userId, "O userId é obrigatório para gerar o Connect Token");

        try {
            String clientUserId = userId.toString();


            CreateConnectTokenRequest request = new CreateConnectTokenRequest(null, clientUserId);

            Response<ConnectTokenResponse> response = pluggyClient.service()
                    .createConnectToken(request)
                    .execute();

            if (response.isSuccessful() && response.body() != null) {
                return response.body().getAccessToken();
            }


            ErrorResponse error = pluggyClient.parseError(response);
            String errorMessage = (error != null && error.getMessage() != null)
                    ? error.getMessage()
                    : "Status HTTP " + response.code();

            throw new RuntimeException("Erro na API da Pluggy ao gerar Connect Token: " + errorMessage);

        } catch (Exception e) {
            throw new RuntimeException("Falha de integração ao solicitar Connect Token para o usuário: " + userId, e);
        }
    }

    @Override
    public ExternalFinancialConnection getItemDetails(String itemId) {

        try {

            Response<ItemResponse> response = pluggyClient.service()
                    .getItem(itemId)
                    .execute();

            if (response.isSuccessful() && response.body() != null) {
                return pluggyFinancialConnectionMapper
                        .toExternalFinancialConnection(response.body());
            }

            ErrorResponse error = pluggyClient.parseError(response);

            String errorMessage =
                    error != null && error.getMessage() != null
                            ? error.getMessage()
                            : "Status HTTP " + response.code();

            throw new RuntimeException(
                    "Erro ao buscar Item na Pluggy: " + errorMessage
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Falha ao buscar Item na Pluggy: " + itemId,
                    e
            );
        }
    }

    @Override
    public List<ExternalFinancialAccount> getAccounts(String itemId) {
        return List.of();
    }


}