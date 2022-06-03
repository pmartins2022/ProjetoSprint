package com.grupo2.utilizadores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe que cria excecoes referentes a erros gerais do servico.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroGeralException extends RuntimeException
{
    public ErroGeralException(String message)
    {
        super(message);
    }
}