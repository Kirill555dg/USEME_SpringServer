package com.example.USEME_SpringServer.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id) {
        super("Задачи с id " + id + " не существует");
    }
}
