package com.example.USEME_SpringServer.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String name) {
        super("Группы с именем " + name + " не существует");
    }
}
