package com.sparta.back_office.exception.manager;

public class SignUpDuplicationException extends RuntimeException {
    public SignUpDuplicationException(String message) {
        super(message);
    }
}
