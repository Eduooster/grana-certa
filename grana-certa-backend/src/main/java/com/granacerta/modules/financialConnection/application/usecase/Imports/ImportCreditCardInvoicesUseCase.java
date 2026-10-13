package com.granacerta.modules.financialConnection.application.usecase.Imports;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.financialConnection.application.gateway.CreditCardInvoiceProviderGateway;
import com.granacerta.modules.financialConnection.application.gateway.ExternalInvoice;
import com.granacerta.modules.financialConnection.application.gateway.FinancialAccountProviderGateway;
import com.granacerta.modules.financialConnection.domain.entity.FinancialConnection;
import com.granacerta.modules.financialConnection.domain.exception.FinancialConnectionNotFoundException;
import com.granacerta.modules.financialConnection.domain.repository.FinancialConnectionRepository;
import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.domain.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ImportCreditCardInvoicesUseCase {

    private final FinancialAccountRepository financialAccountRepository;
    private final FinancialAccountProviderGateway financialAccountProviderGateway;
    private final InvoiceRepository invoiceRepository;
    private final FinancialConnectionRepository financialConnectionRepository;

    public ImportCreditCardInvoicesUseCase(
            FinancialAccountRepository financialAccountRepository,
            FinancialAccountProviderGateway financialAccountProviderGateway,
            InvoiceRepository invoiceRepository, FinancialConnectionRepository financialConnectionRepository
    ) {
        this.financialAccountRepository = financialAccountRepository;
        this.financialAccountProviderGateway = financialAccountProviderGateway;

        this.invoiceRepository = invoiceRepository;
        this.financialConnectionRepository = financialConnectionRepository;
    }


    @Transactional
    public void execute(String externalConnectionId) {

        FinancialConnection financialConnection =
                financialConnectionRepository.findByExternalId(externalConnectionId)
                        .orElseThrow(() ->
                                new FinancialConnectionNotFoundException(
                                        "Could not find connection with id " + externalConnectionId
                                )
                        );

        List<FinancialAccount> accounts =
                financialAccountRepository.findAllByConnectionIdAndFinancialAccountType(
                        financialConnection.getId(),
                        FinancialAccountType.CREDIT
                );

        int created = 0;
        int updated = 0;

        for (FinancialAccount account : accounts) {

            List<ExternalInvoice> externalInvoices =
                    financialAccountProviderGateway.fetchInvoices(account.getExternalId());

            log.info(
                    "Found {} external invoices for account {}",
                    externalInvoices.size(),
                    account.getId()
            );

            for (ExternalInvoice externalInvoice : externalInvoices) {

                Optional<Invoice> existingInvoice =
                        invoiceRepository.findByExternalIdAndAccountId(
                                externalInvoice.externalId(),
                                account.getId()
                        );

                if (existingInvoice.isPresent()) {
                    Invoice invoice = existingInvoice.get();
                    invoice.updateFromExternalData(externalInvoice);
                    invoiceRepository.save(invoice);
                    updated++;
                    continue;
                }

                Invoice invoice = Invoice.createFromConnection(
                        account,
                        externalInvoice
                );

                invoiceRepository.save(invoice);
                created++;
            }
        }

        log.info(
                "Invoice import completed. Created: {}, updated: {}",
                created,
                updated
        );
    }

}