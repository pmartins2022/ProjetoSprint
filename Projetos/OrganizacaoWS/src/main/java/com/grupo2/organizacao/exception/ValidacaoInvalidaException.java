package com.grupo2.organizacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe ValidacaoInvalidaException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidacaoInvalidaException extends RuntimeException
{
    /**
     * Inicializa ValidacaoInvalidaException sem parametross
     */
    public ValidacaoInvalidaException()
    {
    }

    /**
     *Inicializa o massage de ValidacaoInvalidaException com massage
     * @param message o objeto a ser recebido
     */
    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}
