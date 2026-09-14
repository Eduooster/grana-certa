package com.granacerta.modules.financialAccount.domain.exception;

public class FinancialAccountNotFoundException extends RuntimeException {
    public FinancialAccountNotFoundException(String message) {
        super(message);
    }
}
