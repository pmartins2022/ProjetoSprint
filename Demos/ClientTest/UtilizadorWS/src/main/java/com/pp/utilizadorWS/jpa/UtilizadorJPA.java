package com.pp.utilizadorWS.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pp.utilizadorWS.model.TipoUtilizador;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "UTILIZADORES")
public class UtilizadorJPA
{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotEmpty
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TIPOUTILIZADOR")
    private TipoUtilizador tipoUtilizador;

    public UtilizadorJPA()
    {
    }

    public UtilizadorJPA(Long id, String username, String password, TipoUtilizador tipoUtilizador)
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

    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public String getPassword()
    {
        return password;
    }
}
