package com.grupo2.anoLetivoWS.exception;

public class ErrorDetail
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