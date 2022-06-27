package com.grupo2.organizacao.dto;


public class UtilizadorDTO
{
    private Long id;
    private String username;
    private String password;
    private String tipoUtilizador;

    public UtilizadorDTO()
    {
    }

    public UtilizadorDTO(Long id, String username, String password, String tipoUtilizador)
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

    public String getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(String tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }

    @Override
    public String toString()
    {
        return "UtilizadorDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tipoUtilizador=" + tipoUtilizador +
                '}';
    }
}