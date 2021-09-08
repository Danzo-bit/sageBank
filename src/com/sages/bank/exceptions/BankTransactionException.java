package com.sages.bank.exceptions;

public class BankTransactionException extends BankException {
    public BankTransactionException() {
    }

    public BankTransactionException(String message) {
        super(message);
    }

    public BankTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankTransactionException(Throwable cause) {
        super(cause);
    }
}
