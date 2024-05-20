package com.example.USEME_SpringServer.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email){
        super("Пользователя с почтой " + email + " не существует");
    }
}
