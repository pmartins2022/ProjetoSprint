package com.example.javafx.exception;


public class RestPostException extends RuntimeException
{
    public RestPostException(){}

    public RestPostException(String message)
    {
        super(message);
    }
}