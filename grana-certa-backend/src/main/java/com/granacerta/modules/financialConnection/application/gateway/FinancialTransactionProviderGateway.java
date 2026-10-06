package com.granacerta.modules.financialConnection.application.gateway;

import ai.pluggy.client.response.TransactionsResponse;

import java.util.List;

public interface FinancialTransactionProviderGateway {

    List<ExternalTransaction> fetchTransactions(String accountId);

    List<ExternalTransaction> getCreatedTransactions(String createdTransactionsLink);
}