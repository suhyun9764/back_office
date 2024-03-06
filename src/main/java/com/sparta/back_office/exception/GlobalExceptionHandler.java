package com.sparta.back_office.exception;

import com.sparta.back_office.exception.lecture.NotFoundLectureException;
import com.sparta.back_office.exception.manager.NotFoundByEmailException;
import com.sparta.back_office.exception.manager.SignUpDuplicationException;
import com.sparta.back_office.exception.manager.SignUpInputException;
import com.sparta.back_office.exception.manager.WrongPasswordException;
import com.sparta.back_office.exception.teacher.NotFoundByTeacherId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignUpInputException.class)
    public ResponseEntity<String> handleSignUpInputException(SignUpInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SignUpDuplicationException.class)
    public ResponseEntity<String> handleSignUpDuplicationException(SignUpDuplicationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(NotFoundByEmailException.class)
    public ResponseEntity<String> handleNotFoundByEmailException(NotFoundByEmailException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<String> handleWrongPasswordException(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundByTeacherId.class)
    public ResponseEntity<String> handleNotFoundByTeacherId(NotFoundByTeacherId ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundLectureException.class)
    public ResponseEntity<String> handleNotFoundLectureException(NotFoundLectureException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//        return ResponseEntity.badRequest().body("유효성 검사에 실패했습니다: " + ex.getMessage());
//    }

}