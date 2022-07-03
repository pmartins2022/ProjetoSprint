package com.grupo2.utilizadores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe que representa um erro relacionado com uma validacao invalida.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoInvalidaException extends RuntimeException
{
    public ValidacaoInvalidaException(){}

    public ValidacaoInvalidaException(String message)
    {
        super(message);
    }
}