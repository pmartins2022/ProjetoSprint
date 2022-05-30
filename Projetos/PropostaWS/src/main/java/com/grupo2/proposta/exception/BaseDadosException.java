package com.grupo2.proposta.exception;

public class BaseDadosException extends RuntimeException
{
    public BaseDadosException(){}

    public BaseDadosException(String message)
    {
        super(message);
    }
}