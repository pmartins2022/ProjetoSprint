package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.TipoUtilizador;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;
import com.grupo2.projeto.model.annotations.Unique;

@Table(tableName = "UTILIZADOR")
public class UtilizadorDTO extends JDBCTable
{
    @PrimaryKey( generated = false)
    private Long id;
    private String nome;
    private String sobrenome;
    @Unique
    private String email;
    private TipoUtilizador tipoUtilizador;

    public UtilizadorDTO()
    {
    }

    public UtilizadorDTO(Long id, String nome, String sobrenome, String email, String tipoUtilizador)
    {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = TipoUtilizador.valueOf(tipoUtilizador);
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
