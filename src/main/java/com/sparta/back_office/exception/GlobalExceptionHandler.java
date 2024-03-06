package com.sparta.back_office.exception;

import com.sparta.back_office.exception.manager.SignUpDuplicationException;
import com.sparta.back_office.exception.manager.SignUpInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignUpInputException.class)
    public ResponseEntity<String> handleBookNotFoundException(SignUpInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SignUpDuplicationException.class)
    public ResponseEntity<String> handleBookNotFoundException(SignUpDuplicationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }




}