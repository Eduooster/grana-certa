package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service;

import ai.pluggy.client.PluggyClient;
import ai.pluggy.client.request.TransactionsSearchRequest;
import ai.pluggy.client.response.ErrorResponse;
import ai.pluggy.client.response.Transaction;
import ai.pluggy.client.response.TransactionsResponse;
import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.financialConnection.application.gateway.FinancialTransactionProviderGateway;
import com.granacerta.modules.financialConnection.infra.persistence.adapter.PluggyTransactionMapper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import retrofit2.Response;

import java.util.List;
import java.util.Objects;

@Slf4j

@Component
public class PluggyTransactionsClient   {

    private final PluggyClient pluggyClient;
    private static final String clientId=  System.getenv("PLUGGY_CLIENT_ID");
    private static final String clientSecret=  System.getenv("PLUGGY_CLIENT_SECRET");
    private final PluggyTransactionMapper pluggyTransactionMapper ;



    public PluggyTransactionsClient(PluggyTransactionMapper pluggyTransactionMapper) {
        this.pluggyTransactionMapper = pluggyTransactionMapper;
        if (clientId == null || clientSecret == null) {
            throw new IllegalStateException("Environment variables not set");
        }



        this.pluggyClient = PluggyClient.builder()
                .clientIdAndSecret(clientId, clientSecret)
                .build();

    }


    public List<ExternalTransaction> fetchTransactions(String accountId) {

        Objects.requireNonNull(
                accountId,
                "A conta é obrigatória para buscar as transações"
        );

        log.info(
                "Calling Pluggy transactions endpoint. AccountId: {}",
                accountId
        );

        try {

            TransactionsSearchRequest request =
                    new TransactionsSearchRequest()
                            .pageSize(500);

            Response<TransactionsResponse> response =
                    pluggyClient.service()
                            .getTransactions(accountId,request)
                            .execute();

            log.info(
                    "Pluggy transactions response. HTTP Status: {}, Successful: {}",
                    response.code(),
                    response.isSuccessful()
            );

            if (response.isSuccessful() && response.body() != null) {

                log.info(
                        "Pluggy transactions response received successfully. AccountId: {}",
                        accountId
                );

                return pluggyTransactionMapper.toExternalTransactions(
                        response.body()
                );
            }

            ErrorResponse error =
                    pluggyClient.parseError(response);

            String errorMessage =
                    error != null && error.getMessage() != null
                            ? error.getMessage()
                            : "Status HTTP " + response.code();

            log.error(
                    "Pluggy transactions request failed. AccountId: {}, Status: {}, Error: {}",
                    accountId,
                    response.code(),
                    errorMessage
            );

            throw new RuntimeException(
                    "Erro na API da Pluggy ("
                            + accountId
                            + "): "
                            + errorMessage
            );

        } catch (Exception e) {

            log.error(
                    "Exception while requesting transactions from Pluggy. AccountId: {}",
                    accountId,
                    e
            );

            throw new RuntimeException(
                    "Falha de integração ao solicitar as transações da API: "
                            + accountId,
                    e
            );
        }
    }
}
