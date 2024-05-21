package com.example.USEME_SpringServer.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(){
        super("Вы ввели неверный пароль");
    }
}
