package com.grupo2.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe Erro Geral
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroGeralException extends RuntimeException
{
    /**
     * Inicializa ErroGeralException sem parametross
     */
    public ErroGeralException()
    {
    }

    /**
     * Inicializa o massage de ErroGeralException com massage
     * @param message o objeto a ser recebido
     */
    public ErroGeralException(String message)
    {
        super(message);
    }
}
