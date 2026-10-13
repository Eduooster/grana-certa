package com.granacerta.modules.financialConnection.application.usecase.SyncConnection;

import ai.pluggy.client.response.ItemProductState;
import ai.pluggy.client.response.ItemStatusDetail;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportCreditCardInvoicesUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportFinancialAccountsUseCase;
import com.granacerta.modules.financialConnection.application.usecase.Imports.ImportTransactionsUseCase;
import com.granacerta.modules.financialConnection.infra.persistence.external.pluggy.client.dto.ExternalFinancialConnection;

public class SynchronizePartiallyUpdatedProducts {

    private final ImportTransactionsUseCase importTransactionsUseCase;
    private final ImportFinancialAccountsUseCase importFinancialAccountsUseCase;
    private final ImportCreditCardInvoicesUseCase importCreditCardInvoicesUseCase;

    public SynchronizePartiallyUpdatedProducts(ImportTransactionsUseCase importTransactionsUseCase, ImportFinancialAccountsUseCase importFinancialAccountsUseCase, ImportCreditCardInvoicesUseCase importCreditCardInvoicesUseCase) {
        this.importTransactionsUseCase = importTransactionsUseCase;
        this.importFinancialAccountsUseCase = importFinancialAccountsUseCase;
        this.importCreditCardInvoicesUseCase = importCreditCardInvoicesUseCase;
    }

    public void execute(ExternalFinancialConnection externalFinancialConnection) {


        ItemStatusDetail statusDetail = externalFinancialConnection.itemStatusDetail()  ;

        if (statusDetail == null) {
            return;
        }

        boolean accountsUpdated = isUpdated(statusDetail.getAccounts());
        boolean transactionsUpdated = isUpdated(statusDetail.getTransactions());
        boolean creditCardsUpdated = isUpdated(statusDetail.getCreditCards());

        if (accountsUpdated) {
            importFinancialAccountsUseCase.execute(externalFinancialConnection.externalId());
        }

        if (transactionsUpdated) {
            importTransactionsUseCase.execute(externalFinancialConnection.externalId());
        }

        if (creditCardsUpdated) {
            importCreditCardInvoicesUseCase.execute(externalFinancialConnection.externalId());
        }
    }

    private boolean isUpdated(ItemProductState productState) {
        return productState != null && productState.isUpdated();
    }
}
