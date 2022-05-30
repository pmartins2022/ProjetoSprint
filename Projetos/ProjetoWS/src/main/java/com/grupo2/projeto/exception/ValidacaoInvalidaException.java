package com.grupo2.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoInvalidaException extends RuntimeException
{
    public ValidacaoInvalidaException(){}

    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}
