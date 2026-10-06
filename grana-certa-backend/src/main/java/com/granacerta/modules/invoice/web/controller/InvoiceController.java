package com.granacerta.modules.invoice.web.controller;

import com.granacerta.modules.invoice.application.usecase.CreateInvoiceCommand;
import com.granacerta.modules.invoice.application.usecase.CreateInvoiceUseCase;
import com.granacerta.modules.invoice.web.dto.CreateInvoiceRequest;
import com.granacerta.modules.invoice.web.mapper.InvoiceWebMapperr;
import com.granacerta.security.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final CreateInvoiceUseCase createInvoiceUseCase;
    private final InvoiceWebMapperr invoiceWebMapperr;

    @PostMapping
    public ResponseEntity<Void> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody CreateInvoiceRequest request
    ) {

        CreateInvoiceCommand command =
                invoiceWebMapperr.toCommand(
                        userDetails.getUserId(),
                        request
                );


        UUID invoiceId =
                createInvoiceUseCase.execute(command);

        URI location =
                URI.create("/invoices/" + invoiceId);

        return ResponseEntity
                .created(location)
                .build();
    }
}
