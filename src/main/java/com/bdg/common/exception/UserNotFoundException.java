package com.bdg.common.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("User with id: {%d} not found...", id));
    }

    public UserNotFoundException(String emailAddress) {
        super(String.format("User with user email address: {%s} not found...", emailAddress));
    }
}