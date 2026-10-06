package com.granacerta.modules.financialConnection.domain.exception;

public class SyncFailedException extends RuntimeException {
    public SyncFailedException(String message,Throwable cause) {
        super(message);
    }
}
