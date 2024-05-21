package com.example.USEME_SpringServer.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyError {
    private Integer status;
    private String error;
}
