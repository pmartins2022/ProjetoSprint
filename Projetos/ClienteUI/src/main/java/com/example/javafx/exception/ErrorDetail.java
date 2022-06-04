package com.example.javafx.exception;

/**
 * Classe excecao especial em que se pode construir uma excecao com mensagens de erro mais detalhadas.
 * Contem informacao sobre o titulo do erro, status code, e mensagem detalhada do erro.
 * E possivel associar esta excecao a um objeto de resposta HTTP.
 */
public class ErrorDetail extends RuntimeException
{
    private String title;
    private int status;
    private String detail;

    public ErrorDetail()
    {
    }

    public ErrorDetail(String title, int status, String detail)
    {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}