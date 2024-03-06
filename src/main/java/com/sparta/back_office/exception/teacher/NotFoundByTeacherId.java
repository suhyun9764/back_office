package com.sparta.back_office.exception.teacher;

public class NotFoundByTeacherId extends RuntimeException {
    public NotFoundByTeacherId(String message) {
        super(message);
    }
}
