package com.granacerta.modules.transaction.domain.exception;

public class TransactionUpdateNotAllowedException extends RuntimeException {
    public TransactionUpdateNotAllowedException(String message) {
        super(message);
    }
}
