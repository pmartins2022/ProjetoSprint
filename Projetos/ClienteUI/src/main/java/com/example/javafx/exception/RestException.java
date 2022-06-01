package com.example.javafx.exception;


public class RestException extends RuntimeException
{
    public RestException(){}

    public RestException(String message)
    {
        super(message);
    }
}