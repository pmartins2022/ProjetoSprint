package com.example.javafx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase excecao para quando encontrou uma lista vazia.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ListaVaziaException extends RuntimeException
{
    public ListaVaziaException()
    {
    }

    public ListaVaziaException(String message)
    {
        super(message);
    }
}
