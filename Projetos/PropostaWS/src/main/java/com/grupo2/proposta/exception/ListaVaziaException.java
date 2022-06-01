package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListaVaziaException extends RuntimeException
{
    public ListaVaziaException(){}

    public ListaVaziaException(String message)
    {
        super(message);
    }
}
