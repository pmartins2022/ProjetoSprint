package com.grupo2.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe RestExceptionHandler
 */
@ControllerAdvice
public class RestExceptionHandler
{
    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo ErroGeralException
     * @return ResponseEnntity erroDetail e um BAD_REQUEST
     */
    @ExceptionHandler(ErroGeralException.class)
    public ResponseEntity<?> handleErroGeralException(ErroGeralException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Erro geral");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptionalVazioException.class)
    public ResponseEntity<?> handleOptionalVazioException(OptionalVazioException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("ESQUECE");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}