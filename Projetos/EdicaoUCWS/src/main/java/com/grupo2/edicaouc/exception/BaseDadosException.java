package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe BaseDadosException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseDadosException extends RuntimeException
{
    /**
     * Inicializa BaseDadosException sem parâmetros
     */
    public BaseDadosException(){}

    /**
     * Inicializa BaseDadosException parâmetros (message)
     * @param message objeto a ser recebido
     */
    public BaseDadosException(String message)
    {
        super(message);
    }
}