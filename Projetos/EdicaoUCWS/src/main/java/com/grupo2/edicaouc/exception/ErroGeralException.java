package com.grupo2.edicaouc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroGeralException extends RuntimeException
{
    public ErroGeralException()
    {
    }

    public ErroGeralException(String message)
    {
        super(message);
    }
}
