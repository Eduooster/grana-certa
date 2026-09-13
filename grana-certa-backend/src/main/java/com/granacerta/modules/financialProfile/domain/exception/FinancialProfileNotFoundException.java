package com.granacerta.modules.financialProfile.domain.exception;

public class FinancialProfileNotFoundException extends RuntimeException {
    public FinancialProfileNotFoundException(String message) {
        super(message);
    }
}
