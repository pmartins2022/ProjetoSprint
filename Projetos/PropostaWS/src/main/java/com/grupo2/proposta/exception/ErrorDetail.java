package com.grupo2.proposta.exception;

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

    /**
     * Inicializa ErrorDetail sem parametros
     */
    public ErrorDetail()
    {
    }

    /**
     * Inicializa o title, status e detail de ErrorDetail com o title, status e detail
     * @param title e o title de ErrorDetail
     * @param status e o status de ErrorDetail
     * @param detail e o detail de ErrorDetail
     */
    public ErrorDetail(String title, int status, String detail)
    {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    /**
     * Devolve o title de ErrorDetail
     * @return o title de ErrorDetail
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Modifica o title de ErrorDetail
     * @param title e o novo title de ErrorDetail
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Devolve o status de ErrorDetail
     * @return o status de ErrorDetail
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * Modifica o status de ErrorDetail
     * @param status e o novo status de ErrorDetail
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     *  Devolve o detail de ErrorDetail
     * @return o detail de ErrorDetail
     */
    public String getDetail()
    {
        return detail;
    }

    /**
     * Modifica o detail de ErrorDetail
     * @param detail e o novo detail de ErrorDetail
     */
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
}