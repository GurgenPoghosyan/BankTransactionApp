package com.bdg.common.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super(String.format("Account with id: {%d} not found...", id));
    }

    public AccountNotFoundException(String emailAddress) {
        super(String.format("Account with user email address: {%s} not found...", emailAddress));
    }
}