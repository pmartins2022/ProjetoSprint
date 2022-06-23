package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionalVazioException extends RuntimeException
{
    /**
     * Inicializa OptionalVazioException sem parametros
     */
    public OptionalVazioException() {}

    /**
     * Inicializa a massage de OptionalVazioException com a massage
     * @param message e a massage de OptionalVazioException
     */
    public OptionalVazioException(String message)
    {
        super(message);
    }
}
