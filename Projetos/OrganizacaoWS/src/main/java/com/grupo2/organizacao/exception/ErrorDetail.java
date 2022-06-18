package com.grupo2.organizacao.exception;

/**
 * Classe ErroDetail
 */
public class ErrorDetail
{

    private String title;

    private int status;

    private String detail;

    /**
     * Inicializa o ErrorDetail sem parametros
     */
    public ErrorDetail()
    {
    }

    /**
     * Inicializa title, status, detail o ErrorDetail com title, status, detail
     * @param title e o title do ErroDetail
     * @param status e o status do ErroDetail
     * @param detail e o detail do ErroDetail
     */
    public ErrorDetail(String title, int status, String detail)
    {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    /**
     * Devolve o title do ErroDetail
     * @return o title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Modifica o title
     * @param title novo title do ErroDetail
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Devolve o status do ErroDetail
     * @return o status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * Modifica o status
     * @param status novo status do ErroDetail
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     * Devolve o detail do ErroDetail
     * @return o detail
     */
    public String getDetail()
    {
        return detail;
    }

    /**
     * Modifica o detail
     * @param detail novo detail do ErroDetail
     */
    public void setDetail(String detail)
    {
        this.detail = detail;
    }

}