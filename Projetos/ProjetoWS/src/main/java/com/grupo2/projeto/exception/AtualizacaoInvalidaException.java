package com.grupo2.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class AtualizacaoInvalidaException extends RuntimeException
{
    public AtualizacaoInvalidaException(){}

    public AtualizacaoInvalidaException(String message)
    {
        super(message);
    }
}
