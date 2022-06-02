package com.grupo2.utilizadores.dto;

import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;

import java.util.Objects;

public class UtilizadorDTO
{
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private TipoUtilizador tipoUtilizador;

    public UtilizadorDTO()
    {
    }

    public UtilizadorDTO(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    public UtilizadorDTO(String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
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

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSobrenome()
    {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome)
    {
        this.sobrenome = sobrenome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public TipoUtilizador getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(TipoUtilizador tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
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
