package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler(ValidacaoInvalidaException.class)
    public ResponseEntity<?> handleValidacaoInvalidaException(ValidacaoInvalidaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Erro de validação");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}