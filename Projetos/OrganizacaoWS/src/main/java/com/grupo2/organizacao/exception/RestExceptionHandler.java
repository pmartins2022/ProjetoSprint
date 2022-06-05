package com.grupo2.organizacao.exception;

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
     * @param ex objeto do tipo handleOptionalVazioException
     * @return ResponseEntity erroDetail e um BAD_REQUEST
     */
    @ExceptionHandler(ValidacaoInvalidaException.class)
    public ResponseEntity<?> handleOptionalVazioException(ValidacaoInvalidaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Problema na validação.");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo ListaVaziaException
     * @return ResponseEntity erroDetail e um NOT_FOUND
     */
    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<?> handleListaVaziaException(ListaVaziaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Lista vazia.");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}