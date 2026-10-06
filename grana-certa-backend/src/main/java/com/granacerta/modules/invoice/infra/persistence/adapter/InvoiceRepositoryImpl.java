package com.granacerta.modules.invoice.infra.persistence.adapter;

import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.domain.repository.InvoiceRepository;
import com.granacerta.modules.invoice.infra.persistence.mapper.InvoiceEntityMapper;
import com.granacerta.modules.invoice.infra.persistence.repository.JpaInvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final JpaInvoiceRepository jpaInvoiceRepository;
    private final InvoiceEntityMapper invoiceEntityMapper;



    @Override
    public Invoice save(Invoice invoice) {
        return invoiceEntityMapper.toDomain(jpaInvoiceRepository.save(invoiceEntityMapper.toEntity(invoice)));
    }

    @Override
    public Optional<Invoice> findByAccountIdAndPeriod(UUID accountId, LocalDate date) {
        return jpaInvoiceRepository.findByAccountIdAndOpeningDateLessThanEqualAndClosingDateGreaterThanEqual(
                accountId, date,date
        ).map(invoiceEntityMapper::toDomain);
    }
}
