package com.grupo2.utilizadores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe OptionalVazioException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionalVazioException extends RuntimeException
{
    /**
     * Inicializa OptionalVazioException sem parametross
     */
    public OptionalVazioException()
    {
    }

    /**
     * Inicializa o massage de OptionalVazioException com massage
     * @param message o objeto a ser recebido
     */
    public OptionalVazioException(String message)
    {
        super(message);
    }
}
