package com.grupo2.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe que trata as excecoes e cria objetos do tipo ErrorDetails para serem enviados ao cliente.
 */
@ControllerAdvice
public class RestExceptionHandler
{
    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo ValidacaoInvalidaException
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
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo BaseDadosException
     * @return ResponseEntity erroDetail e um BAD_REQUEST
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
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo IdInvalidoException
     * @return ResponseEntity erroDetail e um NOT_FOUND
     */
    @ExceptionHandler(IdInvalidoException.class)
    public ResponseEntity<?> handleIdInvalidoException(IdInvalidoException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Valor nao encontrado.");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * Chamado automaticamente pelo Spring Boot. Instancia e retorna um response entity com informacao do erro.
     * @param ex objeto do tipo AtualizacaoInvalidaException
     * @return ResponseEntity erroDetail e um BAD_REQUEST
     */
    @ExceptionHandler(AtualizacaoInvalidaException.class)
    public ResponseEntity<?> handleAtualizacaoInvalidaException(AtualizacaoInvalidaException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Atualizacao invalida.");
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
        errorDetail.setTitle("Lista vazia");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OptionalVazioException.class)
    public ResponseEntity<?> handleOptionalVazioExceptionException(OptionalVazioException ex)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Erro");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}