package com.grupo2.utilizadores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListaVaziaException extends RuntimeException
{
    public ListaVaziaException(String message)
    {
        super(message);
    }
}
