package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Classe OptionalVazioException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OptionalVazioException extends RuntimeException
{/**
 * Inicializa OptionalVazioException sem parâmetros
 */
    public OptionalVazioException()
    {
    }
    /**
     * Inicializa OptionalVazioException com parâmetros (message)
     * @param message o objeto a ser recebido
     */
    public OptionalVazioException(String message)
    {
        super(message);
    }
}
