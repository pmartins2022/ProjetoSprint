package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase excecao para quando uma atualizacao foi invalida.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtualizacaoInvalidaException extends RuntimeException
{
    /**
     * Inicializa AtualizacaoInvalidaException sem parametros
     */
    public AtualizacaoInvalidaException(){}

    /**
     * Inicializa massage de AtualizacaoInvalidaException com massage
     * @param message e a massage de AtualizacaoInvalidaException
     */
    public AtualizacaoInvalidaException(String message)
    {
        super(message);
    }
}