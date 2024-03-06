package com.sparta.back_office.exception.manager;

public class NotFoundByEmailException extends RuntimeException {
    public NotFoundByEmailException(String message) {
        super(message);
    }
}
