package com.example.USEME_SpringServer.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("Пользователь с почтой " +email+" уже существует");
    }
}
