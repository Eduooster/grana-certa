package com.granacerta.modules.invoice.application.usecase;

import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountStatus;
import com.granacerta.modules.financialAccount.domain.enums.FinancialAccountType;
import com.granacerta.modules.financialAccount.domain.exception.FinancialAccountNotFoundException;
import com.granacerta.modules.financialAccount.domain.repository.FinancialAccountRepository;
import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.domain.exception.InvalidInvoiceAccountException;
import com.granacerta.modules.invoice.domain.repository.InvoiceRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CreateInvoiceUseCase {

    private final FinancialAccountRepository financialAccountRepository;
    private final InvoiceRepository invoiceRepository;

    public CreateInvoiceUseCase(
            FinancialAccountRepository financialAccountRepository,
            InvoiceRepository invoiceRepository
    ) {
        this.financialAccountRepository = financialAccountRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public UUID execute(CreateInvoiceCommand command) {

        FinancialAccount account = financialAccountRepository
                .findByIdAndUserIdAndActiveTrue(
                        command.accountId(),
                        command.userId(),
                        FinancialAccountStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new FinancialAccountNotFoundException(
                                "Financial account not found"
                        )
                );

        if (!FinancialAccountType.CREDIT.equals(account.getType())) {
            throw new InvalidInvoiceAccountException(
                    "Invoice can only be created for credit card accounts"
            );
        }

        Invoice invoice = Invoice.create(command);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return savedInvoice.getId();
    }
}