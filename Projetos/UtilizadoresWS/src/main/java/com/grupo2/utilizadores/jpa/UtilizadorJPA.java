package com.grupo2.utilizadores.jpa;

import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;

import javax.persistence.*;

@Entity
@Table(name = "Utilizadores")
public class UtilizadorJPA
{
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String email;
    private TipoUtilizador tipoUtilizador;

    public UtilizadorJPA()
    {
    }

    public UtilizadorJPA(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    public long getId()
    {
        return id;
    }


    public String getNome()
    {
        return nome;
    }


    public String getSobrenome()
    {
        return sobrenome;
    }


    public String getEmail()
    {
        return email;
    }


    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

}
