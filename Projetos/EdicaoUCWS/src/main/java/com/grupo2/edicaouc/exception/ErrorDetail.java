package com.grupo2.edicaouc.exception;
/**
 * Classe ErrorDetail
 */
public class ErrorDetail extends RuntimeException
{
    /**
     * title do ErrorDetail
     */
    private String title;
    /**
     * status do ErroDetail
     */
    private int status;
    /**
     * detail do ErroDetail
     */
    private String detail;
    /**
     * Inicializa o ErrorDetail sem parâmetros
     */
    public ErrorDetail()
    {
    }
    /**
     * Inicializa title, status, detail o ErrorDetail com title, status, detail
     * @param title é o title do ErrorDetail
     * @param status é o status do ErrorDetail
     * @param detail é o detail do ErrorDetail
     */
    public ErrorDetail(String title, int status, String detail)
    {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }
    /**
     * Devolve o title do ErroDetail
     * @return o title do ErroDetail
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * Modifica o title do ErroDetail
     * @param title novo title do ErroDetail
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * Devolve o status do ErroDetail
     * @return o status do ErroDetail
     */
    public int getStatus()
    {
        return status;
    }
    /**
     * Modifica o status do ErroDetail
     * @param status novo status do ErroDetail
     */
    public void setStatus(int status)
    {
        this.status = status;
    }
    /**
     * Devolve o detail do ErroDetail
     * @return o detail do ErroDetail
     */
    public String getDetail()
    {
        return detail;
    }
    /**
     * Modifica o detail do ErroDetail
     * @param detail novo detail do ErroDetail
     */
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}