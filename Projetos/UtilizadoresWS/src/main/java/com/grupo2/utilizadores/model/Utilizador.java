package com.grupo2.utilizadores.model;

import com.grupo2.utilizadores.exception.ValidacaoInvalidaException;

public class Utilizador
{
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private TipoUtilizador tipoUtilizador;

    public Utilizador()
    {
    }

    public Utilizador(long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
    {
        validateNome(nome);
        validateSobrenome(sobrenome);
        validateEmail(email);

        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }

    private void validateEmail(String email)
    {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!email.matches(regexPattern))
        {
            throw new ValidacaoInvalidaException("Email Inválido");
        }
    }


    private void validateSobrenome(String sobrenome)
    {
        if (sobrenome.trim().length() < 2)
        {
            throw new ValidacaoInvalidaException("Sobrenome tem que ter no mínimo 3 caracteres");
        }
        char x = ' ';
        for (int i = 0; i < sobrenome.length(); i++)
        {
            x = sobrenome.charAt(i);
            if (!(Character.isAlphabetic(x)))
            {
                throw new ValidacaoInvalidaException("Sobrenome só pode ter caracteres alfabéticos");
            }
            if (Character.isDigit(x))
            {
                throw new ValidacaoInvalidaException("Sobrenome não pode ter números");
            }
            if (Character.isSpaceChar(x))
            {
                throw new ValidacaoInvalidaException("Sobrenome não pode conter espaços em branco");
            }

        }
    }

    private void validateNome(String nome)
    {
        if (nome.trim().length() < 3)
        {
            throw new ValidacaoInvalidaException("Nome tem que ter no mínimo 3 caracteres");
        }
        char x = ' ';
        for (int i = 0; i < nome.length(); i++)
        {
            x = nome.charAt(i);
            if (!(Character.isAlphabetic(x)))
            {
                throw new ValidacaoInvalidaException("Nome só pode ter caracteres alfabéticos");
            }
            if (Character.isDigit(x))
            {
                throw new ValidacaoInvalidaException("Nome não pode ter números");
            }
            if (Character.isSpaceChar(x))
            {
                throw new ValidacaoInvalidaException("Nome não pode conter espaços em branco");
            }

        }
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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
        return "Utilizador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUtilizador=" + tipoUtilizador +
                '}';
    }
}
