package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Classe ListaVaziaException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ListaVaziaException extends RuntimeException
{
    /**
     * Inicializa ListaVaziaException sem parâmetros
     */
    public ListaVaziaException()
    {
    }

    /**
     * Inicializa ListaVaziaException com parâmetros (message)
     * @param message Objeto a ser recebido
     */
    public ListaVaziaException(String message)
    {
        super(message);
    }
}
