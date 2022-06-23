package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.TipoUtilizador;

/**
 * Classe DTO que contem informacao sobre um utilizador.
 */
public class UtilizadorDTO
{
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private TipoUtilizador tipoUtilizador;

    /**
     * Inicializa UtilizadorDTO sem parametros
     */
    public UtilizadorDTO()
    {
    }

    /**
     * Inicializa o id, nome, sobrenome, email e tipoUtilizador de UtilizadorDTO com id, nome, sobrenome, email e tipoUtilizador
     * @param id e o id de UtilizadorDTO
     * @param nome e o nome de UtilizadorDTO
     * @param sobrenome e o sobrenome de UtilizadorDTO
     * @param email e o email de UtilizadorDTO
     * @param tipoUtilizador e o tipoUtilizador de UtilizadorDTO
     */
    public UtilizadorDTO(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    /**
     * Devolve o id de UtilizadorDTO
     * @return o id de UtilizadorDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id de UtilizadorDTO
     * @param id é o novo id de UtilizadorDTO
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o nome de UtilizadorDTO
     * @return o nome de UtilizadorDTO
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Modifica o nome de UtilizadorDTO
     * @param nome e o novo nome de UtilizadorDTO
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Devolve o sobrenome de UtilizadorDTO
     * @return o sobrenome de UtilizadorDTO
     */
    public String getSobrenome()
    {
        return sobrenome;
    }

    /**
     * Modifica o sobrenome de UtilizadorDTO
     * @param sobrenome e o novo sobrenome de UtilizadorDTO
     */
    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    /**
     * Devolve o email de UtilizadorDTO
     * @return o email de UtilizadorDTO
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Modifica o email de UtilizadorDTO
     * @param email e o novo email de UtilizadorDTO
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Devolve o tipoUtilizador de UtilizadorDTO
     * @return o tipoUtilizador de UtilizadorDTO
     */
    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    /**
     * Modifica o tipoUtilizador de UtilizadorDTO
     * @param tipoUtilizador e o novo tipoUtilizador de UtilizadorDTO
     */
    public void setTipoUtilizador(TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }
}
