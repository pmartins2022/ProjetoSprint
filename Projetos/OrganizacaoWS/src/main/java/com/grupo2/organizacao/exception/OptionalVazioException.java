package com.grupo2.organizacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionalVazioException extends RuntimeException
{
    public OptionalVazioException()
    {
    }

    public OptionalVazioException(String message)
    {
        super(message);
    }
}
