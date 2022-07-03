package com.grupo2.utilizadores.dto;

/**
 * Classe que apresenta informacao simples de um utilizador. Usado para confirmar credenciais.
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

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getTipoUtilizador()
    {
        return tipoUtilizador;
    }
}
