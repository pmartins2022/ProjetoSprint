package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe ValidacaoInvalidaException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoInvalidaException extends RuntimeException
{
    /**
     * Inicializa ValidacaoInvalidaException sem parâmetros
     */
    public ValidacaoInvalidaException(){}

    /**
     * Inicializa ValidacaoInvalidaException parâmetros (message)
     * @param message objeto a ser recebido
     */
    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}
