package com.example.USEME_SpringServer.exception;


import jakarta.annotation.Priority;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(value = "com.example.USEME_SpringServer.controller")
@Priority(0)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MyError> catchTaskNotFoundException(TaskNotFoundException e){
        return new ResponseEntity<>(
                new MyError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<MyError> catchUserAlreadyExistException(UserAlreadyExistException e){
        return new ResponseEntity<>(
                new MyError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<MyError> catchUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new MyError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
