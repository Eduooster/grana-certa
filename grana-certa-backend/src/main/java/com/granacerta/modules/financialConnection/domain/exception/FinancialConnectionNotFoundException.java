package com.granacerta.modules.financialConnection.domain.exception;

public class FinancialConnectionNotFoundException extends RuntimeException {
    public FinancialConnectionNotFoundException(String message) {
        super(message);
    }
}
