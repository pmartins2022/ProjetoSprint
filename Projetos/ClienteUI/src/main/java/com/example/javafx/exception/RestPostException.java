package com.example.javafx.exception;

/**
 * Classe de exceção para pedidos REST.
 */
public class RestPostException extends RuntimeException
{
    public RestPostException(){}

    public RestPostException(String message)
    {
        super(message);
    }
}