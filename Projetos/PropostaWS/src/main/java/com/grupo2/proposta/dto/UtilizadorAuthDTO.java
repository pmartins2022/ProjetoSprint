package com.grupo2.proposta.dto;

public class UtilizadorAuthDTO
{
    private Long id;
    private String username;
    private String password;
    private String tipoUtilizador;

    /**
     * Inicializa UtilizadorAuthDTO sem parametros
     */
    public UtilizadorAuthDTO()
    {
    }

    /**
     * Inicializa o id, username, password e tipoUtilizador de UtilizadorAuthDTO com id, username, password e tipoUtilizador
     * @param id e o id de UtilizadorAuthDTO
     * @param username e o username de UtilizadorAuthDTO
     * @param password e a password de UtilizadorAuthDTO
     * @param tipoUtilizador e o tipoUtilizador de UtilizadorAuthDTO
     */
    public UtilizadorAuthDTO(Long id, String username, String password, String tipoUtilizador)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tipoUtilizador = tipoUtilizador;
    }

    /**
     * Devolve o id de UtilizadorAuthDTO
     * @return o id UtilizadorAuthDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id de UtilizadorAuthDTO
     * @param id e o novo id de UtilizadorAuthDTO
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     *  Devolve o username de UtilizadorAuthDTO
     * @return username de UtilizadorAuthDTO
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Modifica o id de UtilizadorAuthDTO
     * @param username e o novo username de UtilizadorAuthDTO
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     *  Devolve o password de UtilizadorAuthDTO
     * @return password de UtilizadorAuthDTO
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Modifica o id de UtilizadorAuthDTO
     * @param password e a nova password de UtilizadorAuthDTO
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     *  Devolve o tipoUtilizador de UtilizadorAuthDTO
     * @return tipoUtilizador de UtilizadorAuthDTO
     */
    public String getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    /**
     * Modifica o id de UtilizadorAuthDTO
     * @param tipoUtilizador e o novo tipoUtilizador de UtilizadorAuthDTO
     */
    public void setTipoUtilizador(String tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }

    /**
     * Devolve um UtilizadorDTO com id, username, password e tipoUtilizador
     * @return id, username, password e tipoUtilizador
     */
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
