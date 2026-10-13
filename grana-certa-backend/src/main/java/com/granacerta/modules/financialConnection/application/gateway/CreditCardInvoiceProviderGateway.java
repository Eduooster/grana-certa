package com.granacerta.modules.financialConnection.application.gateway;

import java.util.List;

public interface CreditCardInvoiceProviderGateway {
    List<ExternalInvoice> fetchInvoices(String externalId);
}
