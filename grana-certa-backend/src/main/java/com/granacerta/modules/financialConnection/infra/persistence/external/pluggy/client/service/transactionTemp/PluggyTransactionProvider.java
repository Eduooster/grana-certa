package com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.service.transactionTemp;

import com.granacerta.modules.financialConnection.application.gateway.ExternalTransaction;
import com.granacerta.modules.financialConnection.application.gateway.FinancialTransactionProviderGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PluggyTransactionProvider
        implements FinancialTransactionProviderGateway {

    private static final String clientId =
            System.getenv("PLUGGY_CLIENT_ID");

    private static final String clientSecret =
            System.getenv("PLUGGY_CLIENT_SECRET");

    private final WebClient webClient;
    private final PluggyTransactionMapperProvisorio mapper;

    public PluggyTransactionProvider(
            PluggyTransactionMapperProvisorio mapper
    ) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.pluggy.ai")
                .build();

        this.mapper = mapper;
    }

    @Override
    public List<ExternalTransaction> fetchTransactions(
            String accountId
    ) {

        log.info(
                "Starting Pluggy transactions fetch. AccountId: {}",
                accountId
        );

        String apiKey = generateApiKey();

        log.info(
                "Pluggy API key generated successfully. AccountId: {}",
                accountId
        );

        List<PluggyTransactionResponse> transactions =
                new ArrayList<>();

        String next = null;
        int page = 1;

        do {

            log.info(
                    "Fetching Pluggy transactions page. AccountId: {}, Page: {}, Next: {}",
                    accountId,
                    page,
                    next
            );

            PluggyTransactionsResponse response =
                    fetchPage(
                            accountId,
                            apiKey,
                            next
                    );

            log.info(
                    "Pluggy transactions page received. AccountId: {}, Page: {}, Transactions: {}, Next: {}",
                    accountId,
                    page,
                    response.results().size(),
                    response.next()
            );

            transactions.addAll(response.results());

            next = response.next();
            page++;

        } while (next != null);

        log.info(
                "Pluggy transactions fetch completed. AccountId: {}, TotalTransactions: {}",
                accountId,
                transactions.size()
        );

        return mapper.toExternalTransactions(transactions);
    }




    @Override
    public List<ExternalTransaction> getCreatedTransactions(
            String createdTransactionsLink
    ) {

        log.info(
                "Fetching created transactions from provider using link: {}",
                createdTransactionsLink
        );

        String apiKey = generateApiKey();

        String urlWithoutCursor =
                UriComponentsBuilder
                        .fromUriString(createdTransactionsLink)
                        .replaceQueryParam("after")
                        .build()
                        .toUriString();

        PluggyTransactionsResponse response =
                fetchCreatedTransactionsPage(
                        urlWithoutCursor,
                        apiKey
                );

        return mapper.toExternalTransactions(
                response.results()
        );
    }

    private PluggyTransactionsResponse fetchPage(
            String accountId,
            String apiKey,
            String next
    ) {

        log.info(
                "Calling Pluggy transactions endpoint. AccountId: {}, Next: {}",
                accountId,
                next
        );

        return webClient.get()
                .uri(uriBuilder -> {

                    uriBuilder
                            .path("/v2/transactions")
                            .queryParam("accountId", accountId);

                    if (next != null) {
                        uriBuilder.queryParam("after", next);
                    }

                    return uriBuilder.build();
                })
                .header("X-API-KEY", apiKey)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response
                                .bodyToMono(String.class)
                                .flatMap(body -> {

                                    log.error(
                                            "Pluggy transactions request failed. Status: {}, Body: {}",
                                            response.statusCode(),
                                            body
                                    );

                                    return Mono.error(
                                            new IllegalStateException(
                                                    "Pluggy transactions request failed. Status: "
                                                            + response.statusCode()
                                                            + ", Body: "
                                                            + body
                                            )
                                    );
                                })
                )
                .bodyToMono(PluggyTransactionsResponse.class)
                .block();
    }


    private PluggyTransactionsResponse fetchCreatedTransactionsPage(
            String createdTransactionsLink,
            String apiKey
    ) {

        log.info(
                "Calling Pluggy created transactions endpoint. Link: {}",
                createdTransactionsLink
        );

        return webClient.get()
                .uri(createdTransactionsLink)
                .header("X-API-KEY", apiKey)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response
                                .bodyToMono(String.class)
                                .flatMap(body -> {

                                    log.error(
                                            "Pluggy created transactions request failed. Status: {}, Body: {}",
                                            response.statusCode(),
                                            body
                                    );

                                    return Mono.error(
                                            new IllegalStateException(
                                                    "Pluggy created transactions request failed. Status: "
                                                            + response.statusCode()
                                                            + ", Body: "
                                                            + body
                                            )
                                    );
                                })
                )
                .bodyToMono(PluggyTransactionsResponse.class)
                .block();
    }

    private String generateApiKey() {

        log.info("Generating Pluggy API key.");

        PluggyAuthRequest body =
                new PluggyAuthRequest(
                        clientId,
                        clientSecret
                );

        PluggyAuthResponse response =
                webClient.post()
                        .uri("/auth")
                        .contentType(
                                MediaType.APPLICATION_JSON
                        )
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(
                                PluggyAuthResponse.class
                        )
                        .block();

        if (response == null || response.apiKey() == null) {

            log.error(
                    "Failed to generate Pluggy API key."
            );

            throw new IllegalStateException(
                    "Failed to generate Pluggy API key"
            );
        }

        log.info(
                "Pluggy API key generated successfully."
        );

        return response.apiKey();
    }
}