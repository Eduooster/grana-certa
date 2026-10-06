package com.granacerta.modules.invoice.web.mapper;

import com.granacerta.modules.invoice.application.usecase.CreateInvoiceCommand;
import com.granacerta.modules.invoice.web.dto.CreateInvoiceRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper (componentModel = "spring")
public interface InvoiceWebMapperr {

    CreateInvoiceCommand toCommand(UUID userId, CreateInvoiceRequest request);
}
