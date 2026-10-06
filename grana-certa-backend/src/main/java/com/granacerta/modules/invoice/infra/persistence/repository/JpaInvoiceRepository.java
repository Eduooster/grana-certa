package com.granacerta.modules.invoice.infra.persistence.repository;

import com.granacerta.modules.invoice.infra.persistence.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface JpaInvoiceRepository extends JpaRepository<InvoiceEntity, Long> {


    Optional<InvoiceEntity> findByAccountIdAndOpeningDateLessThanEqualAndClosingDateGreaterThanEqual(
            UUID accountId,
            LocalDate transactionDateForOpening,
            LocalDate transactionDateForClosing
    );

}
