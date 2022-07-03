package com.example.javafx.dto;

/**
 * Classe de dados para informacoes de autenticacao de um utilizador
 */
public class UtilizadorAuthDTO
{
    private Long id;
    private String username;
    private String password;
    private String tipoUtilizador;

    public UtilizadorAuthDTO()
    {
    }

    public UtilizadorAuthDTO(Long id, String username, String password, String tipoUtilizador)
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
        return "ID: " + id +
                "\nUsername: " + username +
                "\nTipo de Utilizador: " + tipoUtilizador ;
    }
}
