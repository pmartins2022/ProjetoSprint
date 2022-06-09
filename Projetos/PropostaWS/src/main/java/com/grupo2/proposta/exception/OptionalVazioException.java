package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OptionalVazioException extends RuntimeException
{
    public OptionalVazioException(String massege)
    {
    }
}
