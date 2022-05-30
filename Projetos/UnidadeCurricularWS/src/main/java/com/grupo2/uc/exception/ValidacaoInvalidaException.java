package com.grupo2.uc.exception;

public class ValidacaoInvalidaException extends RuntimeException
{
    public ValidacaoInvalidaException(){}

    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}