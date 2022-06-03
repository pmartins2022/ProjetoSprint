package com.grupo2.utilizadores.jpa;

import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;

import javax.persistence.*;

/**
 * Classe JPA de Utilizador
 */
@Entity
@Table(name = "Utilizadores")
public class UtilizadorJPA
{
    /**
     * O id do utilizador
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * o nome do utilizador
     */
    private String nome;
    /**
     * o nome do utilizador
     */
    private String sobrenome;
    /**
     * o nome do utilizador
     */
    @Column(unique = true)
    private String email;
    /**
     * o nome do utilizador
     */
    private TipoUtilizador tipoUtilizador;

    /**
     * Inicializa UtilizadorJPA sem parametros
     */
    public UtilizadorJPA()
    {
    }

    /**
     * Inicializa o id, nome, sobrenome, email, tipoUtilizador do UtilizadorJPA com
     * id, nome, sobrenome, email, tipoUtilizador recebidos
     * @param id e o id do UtilizadorJPA
     * @param nome e o nome do UtilizadorJPA
     * @param sobrenome e o sobrenome do UtilizadorJPA
     * @param email e o email do UtilizadorJPA
     * @param tipoUtilizador e o tipoUtilizador do UtilizadorJPA
     */
    public UtilizadorJPA(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    /**
     * Devolve o id do UtilizadorJPA
     * @return o id do UtilizadorJPA
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Devolve o nome do UtilizadorJPA
     * @return o nome do UtilizadorJPA
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Devolve o sobrenome do UtilizadorJPA
     * @return o sobrenome do UtilizadorJPA
     */
    public String getSobrenome()
    {
        return sobrenome;
    }

    /**
     * Devolve o email do UtilizadorJPA
     * @return o email do UtilizadorJPA
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Devolve o tipoUtilizador do UtilizadorJPA
     * @return o tipoUtilizador do UtilizadorJPA
     */
    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

}
