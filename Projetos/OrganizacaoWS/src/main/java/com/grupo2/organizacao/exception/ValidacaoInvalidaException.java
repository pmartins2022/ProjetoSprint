package com.grupo2.organizacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidacaoInvalidaException extends RuntimeException
{
    public ValidacaoInvalidaException()
    {
    }

    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}
