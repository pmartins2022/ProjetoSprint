package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase excecao para quando ocorreu um problema a validar dados.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoInvalidaException extends RuntimeException
{
    /**
     * Inicializa ValidacaoInvalidaException sem parametros
     */
    public ValidacaoInvalidaException(){}

    /**
     * Inicializa massage de ValidacaoInvalidaException com massage
     * @param message e a massage de ValidacaoInvalidaException
     */
    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}