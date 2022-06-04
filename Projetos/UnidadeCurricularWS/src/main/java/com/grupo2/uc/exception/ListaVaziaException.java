package com.grupo2.uc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe ListaVaziaException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListaVaziaException extends RuntimeException
{
    /**
     * Inicializa ListaVaziaException sem parametross
     */
    public ListaVaziaException(){}

    /**
     *Inicializa o massage de ListaVaziaException com massage
     * @param message o objeto a ser recebido
     */
    public ListaVaziaException(String message)
    {
        super(message);
    }
}
