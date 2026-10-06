package com.granacerta.modules.financialInstitution.domain.expception;

public class FinancialInstitutionNotFoundException extends RuntimeException {
    public FinancialInstitutionNotFoundException(String message) {
        super(message);
    }
}
