package com.grupo2.utilizadores.dto;

import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;

import java.util.Objects;

/**
 * Classe DTO do Utilizador
 */
public class UtilizadorDTO
{
    /**
     * Id do utilizador
     */
    private Long id;
    /**
     * nome do utilizador
     */
    private String nome;
    /**
     * sobrenome do utilizador
     */
    private String sobrenome;
    /**
     * email do utilizador
     */
    private String email;
    /**
     * tipoUtilizador do utilizador
     */
    private String username;
    private String password;

    private TipoUtilizador tipoUtilizador;

    /**
     * Inicializa o UtilizadorDTO sem parametros
     */
    public UtilizadorDTO()
    {
    }

    /**
     * Inicializa o id, nome, sobrenome, email e tipoUtilizador do UtilizadorDTO
     * com iid, nome, sobrenome, email e tipoUtilizador recebidos
     * @param id é o id do utilizador
     * @param nome é o nome do utilizador
     * @param sobrenome é o sobrenome do utilizador
     * @param email é o email do utilizador
     * @param tipoUtilizador é o tipoUtilizador do utilizador
     */
    public UtilizadorDTO(Long id, String nome, String sobrenome, String email, String username, String password, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
        this.username = username;
        this.password = password;
    }

    public UtilizadorDTO(String nome, String sobrenome, String email, String username, String password, TipoUtilizador tipoUtilizador)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
        this.username = username;
        this.password = password;
    }

    /**
     * Devolve o id do utilizador
     * @return o id do utilizador
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id do utilizador
     * @param id novo id do utilizador
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o nome do utilizador
     * @return o nome do utilizador
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Modifica o nome do utilizador
     * @param nome novo nome do utilizador
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * Devolve o sobrenome do utilizador
     * @return o sobrenome do utilizador
     */
    public String getSobrenome()
    {
        return sobrenome;
    }

    /**
     * Modifica o sobrenome do utilizador
     * @param sobrenome novo sobrenome do utilizador
     */
    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    /**
     * Devolve o email do utilizador
     * @return o email do utilizador
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Modifica o email do utilizador
     * @param email novo email do utilizador
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Devolve o tipoUtilizador do utilizador
     * @return o tipoUtilizador do utilizador
     */
    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    /**
     * Modifica o tipoUtilizador do utilizador
     * @param tipoUtilizador novo tipoUtilizador do utilizador
     */
    public void setTipoUtilizador(TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilizadorDTO that = (UtilizadorDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sobrenome, that.sobrenome) && Objects.equals(email, that.email) && tipoUtilizador == that.tipoUtilizador;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, nome, sobrenome, email, tipoUtilizador);
    }

    @Override
    public String toString()
    {
        return "UtilizadorDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUtilizador=" + tipoUtilizador +
                '}';
    }
}
