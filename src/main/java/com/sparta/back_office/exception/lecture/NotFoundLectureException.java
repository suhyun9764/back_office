package com.sparta.back_office.exception.lecture;

public class NotFoundLectureException extends RuntimeException {
    public NotFoundLectureException(String message) {
        super(message);
    }
}
