package com.grupo2.organizacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe OptionalVazioException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionalVazioException extends RuntimeException
{
    /**
     * Instancia OptionalVazioException sem parametros
     */
    public OptionalVazioException()
    {
    }

    /**
     * Instancia OptionalVazioException com paramentro message
     * @param message mensagem de erro
     */
    public OptionalVazioException(String message)
    {
        super(message);
    }
}
