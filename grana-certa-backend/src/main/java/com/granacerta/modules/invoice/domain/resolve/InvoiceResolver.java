package com.granacerta.modules.invoice.domain.resolve;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.domain.exception.InvoiceNotFoundException;
import com.granacerta.modules.invoice.domain.repository.InvoiceRepository;

import java.time.LocalDate;

public class InvoiceResolver {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResolver(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice resolve(
            FinancialAccount account,
            LocalDate transactionDate
    ) {
        if (!FinancialAccountType.CREDIT.equals(account.getType())) {
            return null;
        }

        return invoiceRepository
                .findByAccountIdAndPeriod(
                        account.getId(),
                        transactionDate
                )

                .orElseThrow(()-> new InvoiceNotFoundException("Invoice not found"));
    }
}