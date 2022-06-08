package com.pp.utilizadorWS.model;

public class Utilizador
{
    private Long id;
    private String username;
    private String password;
    private TipoUtilizador tipoUtilizador;

    public Utilizador()
    {
    }

    public Utilizador(Long id, String username, String password, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipoUtilizador = tipoUtilizador;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }
}
