package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase excecao para quando encontrou uma lista vazia.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListaVaziaException extends RuntimeException
{
    /**
     * Inicializa ListaVaziaException sem parametros
     */
    public ListaVaziaException(){}

    /**
     * Inicializa massage de ListaVaziaException com massage
     * @param message e a massage de ListaVaziaException
     */
    public ListaVaziaException(String message)
    {
        super(message);
    }
}
