package com.grupo2.proposta.model;

import com.grupo2.proposta.exception.ValidacaoInvalidaException;

public class Proposta
{
    private long id;
    private long utilizadorId;
    private long organizacaoId;
    private String titulo;
    private String problema;
    private String objetivo;
    private long edicaoUCId;
    private PropostaEstado estadoAtual;

    public Proposta(long id, long utilizadorId, long organizacaoId, String titulo,
                    String problema, String objetivo,
                    long edicaoUCId, PropostaEstado estadoAtual) throws ValidacaoInvalidaException
    {
        this.id = id;
        validate(titulo, problema, objetivo);
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.edicaoUCId = edicaoUCId;
        this.estadoAtual = estadoAtual;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getUtilizadorId()
    {
        return utilizadorId;
    }

    public void setUtilizadorId(long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    public long getOrganizacaoId()
    {
        return organizacaoId;
    }

    public void setOrganizacaoId(long organizacaoId)
    {
        this.organizacaoId = organizacaoId;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getProblema()
    {
        return problema;
    }

    public void setProblema(String problema)
    {
        this.problema = problema;
    }

    public String getObjetivo()
    {
        return objetivo;
    }

    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    public long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    public void setEdicaoUCId(long edicaoUCId)
    {
        this.edicaoUCId = edicaoUCId;
    }

    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }

    public void aprovarProposta()
    {
        this.estadoAtual = PropostaEstado.APROVADO;
    }

    public void reprovarProposta()
    {
        this.estadoAtual = PropostaEstado.REPROVADO;
    }

    private void validate(String titulo, String problema, String objetivo) throws ValidacaoInvalidaException
    {
        validateTitulo(titulo);
        validateObjetivo(objetivo);
        validateProblema(problema);
    }

    private void validateTitulo(String titulo)
    {
        validateString(titulo,10);
        this.titulo = titulo;
    }

    private void validateProblema(String problema)
    {
        validateString(problema,10);
        this.problema = problema;
    }

    private void validateObjetivo(String objetivo)
    {
        validateString(objetivo,10);
        this.objetivo = objetivo;
    }

    private void validateString(String str, int minSize) throws ValidacaoInvalidaException
    {
        if (str.trim().length() < minSize)
        {
            throw new ValidacaoInvalidaException("Valores tem que ter no minimo "+minSize+" caracteres de comprimento.");
        }
    }
}