package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe ErroGeralException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroGeralException extends RuntimeException
{
    /**
     * Instancia ErroGeralException sem parametros
     */
    public ErroGeralException()
    {
    }

    /**
     * Instancia ErroGeralExceptioon com parametro message
     *
     * @param message
     */
    public ErroGeralException(String message)
    {
        super(message);
    }
}
