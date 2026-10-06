package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service;

import ai.pluggy.client.PluggyClient;
import ai.pluggy.client.response.Account;
import ai.pluggy.client.response.AccountsResponse;
import ai.pluggy.client.response.ErrorResponse;
import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.infra.persistence.adapter.PluggyFinancialAccountMapper;
import com.granacerta.modules.financialConnection.infra.persistence.adapter.PluggyFinancialConnectionMapper;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialAccount;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

@Component
public class PluggyFinancialAccountClient implements FinancialAccountProviderGateway {

    private final PluggyClient pluggyClient;
    private static final String clientId=  System.getenv("PLUGGY_CLIENT_ID");
    private static final String clientSecret=  System.getenv("PLUGGY_CLIENT_SECRET");

    private final PluggyFinancialAccountMapper pluggyFinancialAccountMapper;

    public PluggyFinancialAccountClient(PluggyFinancialConnectionMapper pluggyFinancialConnectionMapper, PluggyFinancialConnectionMapper financialConnectionMapper, PluggyFinancialAccountMapper pluggyFinancialAccountMapper) {


        if (clientId == null || clientSecret == null) {
            throw new IllegalStateException("Environment variables not set");
        }

        this.pluggyClient = PluggyClient.builder()
                .clientIdAndSecret(clientId, clientSecret)
                .build();
        this.pluggyFinancialAccountMapper = pluggyFinancialAccountMapper;
    }

    @Override
    public List<ExternalFinancialAccount> fetchAccounts(String itemId) {

        Objects.requireNonNull(
                itemId,
                "O itemId é obrigatório para buscar as contas"
        );

        try {

            Response<AccountsResponse> response =
                    pluggyClient.service()
                            .getAccounts(itemId)
                            .execute();

            if (response.isSuccessful() && response.body() != null) {
                return pluggyFinancialAccountMapper
                        .toExternalFinancialAccounts(response.body());
            }


            ErrorResponse error = pluggyClient.parseError(response);

            String errorMessage =
                    error != null && error.getMessage() != null
                            ? error.getMessage()
                            : "Status HTTP " + response.code();

            throw new RuntimeException(
                    "Erro na API da Pluggy ao buscar as contas ("
                            + itemId
                            + "): "
                            + errorMessage
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Falha de integração ao solicitar as contas do item: "
                            + itemId,
                    e
            );
        }
    }
}
