package com.grupo2.proposta.model;

import com.grupo2.proposta.exception.AtualizacaoInvalidaException;
import com.grupo2.proposta.exception.ValidacaoInvalidaException;

import java.util.Objects;

/**
 * Classe que representa uma proposta de um projeto.
 */
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

    /**
     * Metodo que inicializa a proposta.
     * @param id id da proposta
     * @param utilizadorId id do utilizador
     * @param organizacaoId id da organizacao
     * @param titulo titulo da proposta
     * @param problema problema da proposta
     * @param objetivo objetivo da proposta
     * @param edicaoUCId id da edicao da UC
     * @param estadoAtual estado da proposta
     * @throws ValidacaoInvalidaException
     */
    public Proposta(Long id, Long utilizadorId, Long organizacaoId, String titulo,
                    String problema, String objetivo,
                    Long edicaoUCId, PropostaEstado estadoAtual) throws ValidacaoInvalidaException
    {
        this.id = id;
        validate(titulo, problema, objetivo);
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.edicaoUCId = edicaoUCId;
        if (estadoAtual == null)
        {
            this.estadoAtual = PropostaEstado.CANDIDATURA;
        }
        else
        {
            this.estadoAtual = estadoAtual;
        }
    }

    /**
     * Metodo que inicia uma proposta.
     * @param utilizadorId id do utilizador
     * @param organizacaoId id da organizacao
     * @param titulo titulo da proposta
     * @param problema problema da proposta
     * @param objetivo objetivo da proposta
     * @param edicaoUCId id da edicao da UC
     * @param estadoAtual estado da proposta
     */
    public Proposta(Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId, PropostaEstado estadoAtual)
    {
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        validate(titulo,problema,objetivo);
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.edicaoUCId = edicaoUCId;
        if (estadoAtual == null)
        {
            this.estadoAtual = PropostaEstado.CANDIDATURA;
        }
        else
        {
            this.estadoAtual = estadoAtual;
        }
    }

    /**
     * Devolve o id de Proposta
     * @return o id de Proposta
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id de Proposta
     * @param id o novo id de Proposta
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o utilizadorId de Proposta
     * @return o utilizadorId de Proposta
     */
    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    /**
     * Modifica o utilizadorId de Proposta
     * @param utilizadorId o novo utilizadorId de Proposta
     */
    public void setUtilizadorId(Long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    /**
     * Devolve o organizacaoId de Proposta
     * @return o organizacaoId de Proposta
     */
    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    /**
     * Modifica o organizacaoId de Proposta
     * @param organizacaoId o novo organizacaoId de Proposta
     */
    public void setOrganizacaoId(Long organizacaoId)
    {
        this.organizacaoId = organizacaoId;
    }

    /**
     * Devolve o titulo de Proposta
     * @return o titulo de Proposta
     */
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * Modifica o titulo de Proposta
     * @param titulo o novo titulo de Proposta
     */
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    /**
     * Devolve o problema de Proposta
     * @return o problema de Proposta
     */
    public String getProblema()
    {
        return problema;
    }

    /**
     * Modifica o problema de Proposta
     * @param problema o novo problema de Proposta
     */
    public void setProblema(String problema)
    {
        this.problema = problema;
    }

    /**
     * Devolve o objetivo de Proposta
     * @return o objetivo de Proposta
     */
    public String getObjetivo()
    {
        return objetivo;
    }

    /**
     * Modifica o objetivo de Proposta
     * @param objetivo o novo objetivo de Proposta
     */
    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    /**
     * Devolve o edicaoUCId de Proposta
     * @return o edicaoUCId de Proposta
     */
    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    /**
     * Modifica o edicaoUCId de Proposta
     * @param edicaoUCId o novo edicaoUCId de Proposta
     */
    public void setEdicaoUCId(Long edicaoUCId)
    {
        this.edicaoUCId = edicaoUCId;
    }

    /**
     * Devolve o estadoAtual de Proposta
     * @return o estadoAtual de Proposta
     */
    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }

    /**
     * Modifica o estadoAtual de Proposta
     * @param estadoAtual o novo estadoAtual de Proposta
     */
    public void setEstadoAtual(PropostaEstado estadoAtual)
    {
        this.estadoAtual = estadoAtual;
    }

    /**
     * Metodo que atualiza o estado da proposta, ficando aprovada.
     * @throws AtualizacaoInvalidaException caso ja tenha sido aprovada/rejeitada anteriormente
     */
    public void aprovarProposta() throws AtualizacaoInvalidaException
    {
        if (estadoAtual != PropostaEstado.CANDIDATURA)
        {
            throw new AtualizacaoInvalidaException("Nao e possivel mudar o estado da proposta. Ja se encontra "+this.estadoAtual.name());
        }
        this.estadoAtual = PropostaEstado.APROVADO;
    }

    /**
     * Metodo que atualiza o estado da proposta, ficando reprovada.
     * @throws AtualizacaoInvalidaException caso ja tenha sido aprovada/rejeitada anteriormente
     */
    public void reprovarProposta() throws AtualizacaoInvalidaException
    {
        if (estadoAtual != PropostaEstado.CANDIDATURA)
        {
            throw new AtualizacaoInvalidaException("Nao e possivel mudar o estado da proposta. Ja se encontra "+this.estadoAtual.name());
        }
        this.estadoAtual = PropostaEstado.REPROVADO;
    }

    /**
     * Metodo que valida o titulo, o problema e o objetivo
     * @param titulo o titulo a ser validado
     * @param problema o problema a ser validado
     * @param objetivo o objetivo a ser validado
     * @throws ValidacaoInvalidaException caso 
     */
    private void validate(String titulo, String problema, String objetivo) throws ValidacaoInvalidaException
    {
        validateTitulo(titulo);
        validateObjetivo(objetivo);
        validateProblema(problema);
    }

    /**
     *
     * @param titulo
     */
    private void validateTitulo(String titulo)
    {
        validateString(titulo,MIN_TAMANHO);
        this.titulo = titulo;
    }

    /**
     *
     * @param problema
     */
    private void validateProblema(String problema)
    {
        validateString(problema,MIN_TAMANHO);
        this.problema = problema;
    }

    /**
     *
     * @param objetivo
     */
    private void validateObjetivo(String objetivo)
    {
        validateString(objetivo,MIN_TAMANHO);
        this.objetivo = objetivo;
    }

    /**
     *
     * @param str
     * @param minSize
     * @throws ValidacaoInvalidaException
     */
    private void validateString(String str, int minSize) throws ValidacaoInvalidaException
    {
        if (str.trim().length() < minSize)
        {
            throw new ValidacaoInvalidaException("Valores tem que ter no minimo "+minSize+" caracteres de comprimento.");
        }
    }

    /**
     *
     */
    public void aceitarPropostaProjeto()
    {
        this.estadoAtual = PropostaEstado.EM_PROJETO;
    }

    /**
     * Verifica se dois objetos são iguias
     * @param o objeto a ser conparado
     * @return true ou false, conforma os objetos comparados são iguais ou não
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return utilizadorId.equals(proposta.utilizadorId) && organizacaoId.equals(proposta.organizacaoId) && titulo.equals(proposta.titulo) && problema.equals(proposta.problema) && objetivo.equals(proposta.objetivo) && edicaoUCId.equals(proposta.edicaoUCId) && estadoAtual == proposta.estadoAtual;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual);
    }
}