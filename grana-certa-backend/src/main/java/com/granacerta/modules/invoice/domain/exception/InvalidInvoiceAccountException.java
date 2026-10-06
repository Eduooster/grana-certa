package com.granacerta.modules.invoice.domain.exception;

public class InvalidInvoiceAccountException extends RuntimeException {
    public InvalidInvoiceAccountException(String message) {
        super(message);
    }
}
