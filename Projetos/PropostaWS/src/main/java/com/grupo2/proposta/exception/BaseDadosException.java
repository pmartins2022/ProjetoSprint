package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase excecao para quando ocorrreu um problema a obter ou atualizar dados na base de dados.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseDadosException extends RuntimeException
{
    /**
     * Inicializa BaseDadosException sem parametros
     */
    public BaseDadosException(){}

    /**
     * Inicializa massage de BaseDadosException com massage
     * @param message e a massage de BaseDadosException
     */
    public BaseDadosException(String message)
    {
        super(message);
    }
}