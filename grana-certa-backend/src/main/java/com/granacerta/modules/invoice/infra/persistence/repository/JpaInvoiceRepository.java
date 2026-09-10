package com.granacerta.modules.invoice.infra.persistence.repository;

import com.granacerta.modules.invoice.infra.persistence.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
}
