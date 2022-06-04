package com.grupo2.edicaouc.exception;

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
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informação do erro.
     * @param ex objeto do tipo handleValidacaoInvalidaException
     * @return ResponseEntity erroDetail e um BAD_REQUEST
     */
    @ExceptionHandler(ValidacaoInvalidaException.class)
    public ResponseEntity<?> handleValidacaoInvalidaException(ValidacaoInvalidaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Erro de validação");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informação do erro.
     * @param ex objeto do tipo handleBaseDadosException
     * @return ResponseEntity errorDetail e BAD_REQUEST
     */
    @ExceptionHandler(BaseDadosException.class)
    public ResponseEntity<?> handleBaseDadosException(BaseDadosException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Problema na base de dados.");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informação do erro.
     * @param ex objeto do tipo handleListaVaziaException
     * @return ResponseEntity errorDetail e BAD_REQUEST
     */
    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<?> handleListaVaziaException(ListaVaziaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Lista vazia.");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}