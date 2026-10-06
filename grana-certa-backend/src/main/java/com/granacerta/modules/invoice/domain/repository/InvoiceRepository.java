package com.granacerta.modules.invoice.domain.repository;

import com.granacerta.modules.invoice.domain.entity.Invoice;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    Invoice save(Invoice invoice);

    Optional<Invoice> findByAccountIdAndPeriod(
            UUID accountId,
            LocalDate date
    );
}
