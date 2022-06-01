package com.grupo2.proposta.model;

import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;

public class Proposta
{
    private Long id;
    private Long utilizadorId;
    private Long organizacaoId;
    private String titulo;
    private String problema;
    private String objetivo;
    private Long edicaoUCId;
    private PropostaEstado estadoAtual;

    private static final int MIN_TAMANHO = 10;

    public Proposta(Long id, Long utilizadorId, Long organizacaoId, String titulo,
                    String problema, String objetivo,
                    Long edicaoUCId, PropostaEstado estadoAtual) throws ValidacaoInvalidaException
    {
        this.id = id;
        validate(titulo, problema, objetivo);
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.edicaoUCId = edicaoUCId;
        this.estadoAtual = estadoAtual;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    public void setOrganizacaoId(Long organizacaoId)
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

    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    public void setEdicaoUCId(Long edicaoUCId)
    {
        this.edicaoUCId = edicaoUCId;
    }

    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }

    public void aprovarProposta() throws AtualizacaoInvalidaException
    {
        if (estadoAtual != PropostaEstado.CANDIDATURA)
        {
            throw new AtualizacaoInvalidaException("Nao e possivel mudar o estado da proposta. Ja se encontra "+this.estadoAtual.name());
        }
        this.estadoAtual = PropostaEstado.APROVADO;
    }

    public void reprovarProposta() throws AtualizacaoInvalidaException
    {
        if (estadoAtual != PropostaEstado.CANDIDATURA)
        {
            throw new AtualizacaoInvalidaException("Nao e possivel mudar o estado da proposta. Ja se encontra "+this.estadoAtual.name());
        }
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
        validateString(titulo,MIN_TAMANHO);
        this.titulo = titulo;
    }

    private void validateProblema(String problema)
    {
        validateString(problema,MIN_TAMANHO);
        this.problema = problema;
    }

    private void validateObjetivo(String objetivo)
    {
        validateString(objetivo,MIN_TAMANHO);
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