package com.grupo2.utilizadores.model;

import com.grupo2.utilizadores.exception.OptionalVazioException;

/**
 * Classe de dominio do Utilizador
 */
public class Utilizador
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
    private TipoUtilizador tipoUtilizador;

    /**
     * Variavel auxiliar para validacao do nome
     */
    private static final int NOME_MIN_NUM_CHARACTERS = 3;
    /**
     * Variavel auxiliar para validacao do sobrenome
     */
    private static final int SOBRENOME_MIN_NUM_CHARACTERS = 2;
    /**
     * Variavel auxiliar para validacao do email
     */
    private static final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Inicializa o Projeto sem parametros
     */
    public Utilizador()
    {
    }

    /**
     * Inicializa o id, nome, sobrenome, email, tipoUtilizador do Utilizador com id, nome, sobrenome, email, tipoUtilizador
     * @param id é o id do utilizador
     * @param nome é o nome do utilizador
     * @param sobrenome é o sobrenome do utilizador
     * @param email é o email do utilizador
     * @param tipoUtilizador é o tipoUtilizador do utilizador
     */
    public Utilizador(Long id, String nome, String sobrenome, String email, TipoUtilizador tipoUtilizador)
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

    /**
     * Metodo que valida o email
     * @param email recebe o email a ser validado
     */
    private void validateEmail(String email)
    {
        if (!email.matches(regexPattern))
        {
            throw new OptionalVazioException("Email Inválido");
        }
    }

    /**
     * Metodo que valida o sobrenome
     * @param sobrenome recebe o sobrenome a ser validado
     */
    private void validateSobrenome(String sobrenome)
    {
        if (sobrenome.trim().isEmpty() || sobrenome.length() < SOBRENOME_MIN_NUM_CHARACTERS)
        {
            throw new OptionalVazioException("Sobrenome tem que ter no mínimo 3 caracteres");
        }
        char x;
        for (int i = 0; i < sobrenome.length(); i++)
        {
            x = sobrenome.charAt(i);
            if (!(Character.isAlphabetic(x)))
            {
                throw new OptionalVazioException("Sobrenome só pode ter caracteres alfabéticos");
            }
            if (Character.isDigit(x))
            {
                throw new OptionalVazioException("Sobrenome não pode ter números");
            }
            if (Character.isSpaceChar(x))
            {
                throw new OptionalVazioException("Sobrenome não pode conter espaços em branco");
            }

        }
    }

    /**
     * Metodo que valida o nome
     * @param nome recebe o nome a ser validado
     */
    private void validateNome(String nome)
    {
        if (nome.trim().isEmpty() || nome.length() < NOME_MIN_NUM_CHARACTERS)
        {
            throw new OptionalVazioException("Nome tem que ter no mínimo 3 caracteres");
        }
        char x;
        for (int i = 0; i < nome.length(); i++)
        {
            x = nome.charAt(i);
            if (!(Character.isAlphabetic(x)))
            {
                throw new OptionalVazioException("Nome só pode ter caracteres alfabéticos");
            }
            if (Character.isDigit(x))
            {
                throw new OptionalVazioException("Nome não pode ter números");
            }
            if (Character.isSpaceChar(x))
            {
                throw new OptionalVazioException("Nome não pode conter espaços em branco");
            }

        }
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
        validateNome(nome);
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
        validateSobrenome(sobrenome);
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
        validateEmail(email);
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

    /**
     * Devolve um Utilizador com id, nome do utlizador, sobrenome do utlizador, email do utlizador e tipoUtilizador do utlizador
     * @return id, nome, sobrenome, email e tipoUtilizador
     */
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
