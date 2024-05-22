package com.example.USEME_SpringServer.exception;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException(){
        super("Указанный тип задач не найден");
    }
}
