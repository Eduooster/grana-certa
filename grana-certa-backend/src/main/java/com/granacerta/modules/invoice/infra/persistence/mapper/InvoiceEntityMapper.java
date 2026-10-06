package com.granacerta.modules.invoice.infra.persistence.mapper;

import com.granacerta.modules.invoice.domain.entity.Invoice;
import com.granacerta.modules.invoice.infra.persistence.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceEntityMapper {
    InvoiceEntity toEntity(Invoice   invoiceEntity);
    Invoice toDomain(InvoiceEntity
                     invoiceEntity);
}
