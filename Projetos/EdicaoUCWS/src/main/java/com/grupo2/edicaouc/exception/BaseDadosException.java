package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseDadosException extends RuntimeException
{
    public BaseDadosException(){}

    public BaseDadosException(String message)
    {
        super(message);
    }
}