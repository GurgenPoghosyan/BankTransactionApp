package com.bdg.common.exception;

public class BalanceNotAvailableException extends RuntimeException{
    public BalanceNotAvailableException() {
        super("Balance is not available");
    }
}
