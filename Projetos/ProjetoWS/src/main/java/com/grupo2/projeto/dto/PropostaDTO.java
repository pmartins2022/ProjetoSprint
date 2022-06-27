package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe DTO que contem informacao sobre uma proposta.
 */
@Table(tableName = "PROPOSTA")
public class PropostaDTO extends JDBCTable implements Serializable
{
    /**
     * id da PropostaDTO
     */
    @PrimaryKey(generated = false)
    private Long id;
    /**
     * id de utilizador
     */
    @ForeignKey( className = UtilizadorDTO.class, fieldName = "ID")
    private Long utilizadorId;
    /**
     * id de organização
     */
    @ForeignKey( className = OrganizacaoDTO.class, fieldName = "NIF")
    private Long organizacaoId;
    /**
     * título da PropostDTO
     */
    private String titulo;
    /**
     * problema da PropostaDTO
     */
    private String problema;
    /**
     * objetivo da PropostaDTO
     */
    private String objetivo;
    /**
     * id de EdicaoUC
     */
    @ForeignKey( className = EdicaoUCDTO.class, fieldName = "ID")
    private Long edicaoUCId;
    /**
     * estado da PropostaDTO
     */
    private PropostaEstado estadoAtual;

    /**
     * Inicializa PropostaDTO sem parametros
     */
    public PropostaDTO()
    {
    }

    /**
     * Inicializa id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual de PropostaDTO
     * com o id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual
     * @param id e o id de PropostaDTO
     * @param utilizadorId e o utilizadorId de PropostaDTO
     * @param organizacaoId e o organizacaoId de PropostaDTO
     * @param titulo e o titulo de PropostaDTO
     * @param problema e o problema de PropostaDTO
     * @param objetivo e o objetivo de PropostaDTO
     * @param edicaoUCId e o edicaoUCId de PropostaDTO
     * @param estadoAtual e o estadoAtual de PropostaDTO
     */
    public PropostaDTO(Long id, Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId, PropostaEstado estadoAtual)
    {
        this.id = id;
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.edicaoUCId = edicaoUCId;
        this.estadoAtual = estadoAtual;
    }

    /**
     * Inicializa utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual de PropostaDTO
     * com o utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual
     * @param utilizadorId e o utilizadorId de PropostaDTO
     * @param organizacaoId e o organizacaoId de PropostaDTO
     * @param titulo e o titulo de PropostaDTO
     * @param problema e o problema de PropostaDTO
     * @param objetivo e o objetivo de PropostaDTO
     * @param edicaoUCId e o edicaoUCId de PropostaDTO
     * @param estadoAtual e o estadoAtual de PropostaDTO
     */
    public PropostaDTO(Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId, PropostaEstado estadoAtual)
    {
        this.utilizadorId = utilizadorId;
        this.organizacaoId = organizacaoId;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.edicaoUCId = edicaoUCId;
        this.estadoAtual = estadoAtual;
    }

    /**
     * Devolve o id de PropostaDTO
     * @return id de PropostaDTO
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Modifica o id de PropostaDTO
     * @param id e o novo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Devolve o utilizadorId de PropostaDTO
     * @return id de PropostaDTO
     */
    public Long getUtilizadorId()
    {
        return utilizadorId;
    }

    /**
     * Modifica o utilizadorId de PropostaDTO
     * @param utilizadorId e o novo utilizadorId
     */
    public void setUtilizadorId(Long utilizadorId)
    {
        this.utilizadorId = utilizadorId;
    }

    /**
     * Devolve o utilizadorId de PropostaDTO
     * @return utilizadorId de PropostaDTO
     */
    public Long getOrganizacaoId()
    {
        return organizacaoId;
    }

    /**
     * Modifica o organizacaoId de PropostaDTO
     * @param organizacaoId e o novo organizacaoId
     */
    public void setOrganizacaoId(Long organizacaoId)
    {
        this.organizacaoId = organizacaoId;
    }

    /**
     * Devolve o titulo de PropostaDTO
     * @return titulo de PropostaDTO
     */
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * Modifica o titulo de PropostaDTO
     * @param titulo e o novo titulo
     */
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    /**
     * Devolve o problema de PropostaDTO
     * @return problema de PropostaDTO
     */
    public String getProblema()
    {
        return problema;
    }

    /**
     * Modifica o problema de PropostaDTO
     * @param problema e o novo problema
     */
    public void setProblema(String problema)
    {
        this.problema = problema;
    }

    /**
     * Devolve o objetivo de PropostaDTO
     * @return objetivo de PropostaDTO
     */
    public String getObjetivo()
    {
        return objetivo;
    }

    /**
     * Modifica o objetivo de PropostaDTO
     * @param objetivo e o novo objetivo
     */
    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    /**
     * Devolve o edicaoUCId de PropostaDTO
     * @return edicaoUCId de PropostaDTO
     */
    public Long getEdicaoUCId()
    {
        return edicaoUCId;
    }

    /**
     * Modifica o edicaoUCId de PropostaDTO
     * @param edicaoUCId e o novo edicaoUCId
     */
    public void setEdicaoUCId(Long edicaoUCId)
    {
        this.edicaoUCId = edicaoUCId;
    }

    /**
     * Devolve o estadoAtual de PropostaDTO
     * @return estadoAtual de PropostaDTO
     */
    public PropostaEstado getEstadoAtual()
    {
        return estadoAtual;
    }

    /**
     * Modifica o estadoAtual de PropostaDTO
     * @param estadoAtual e o novo estadoAtual
     */
    public void setEstadoAtual(PropostaEstado estadoAtual)
    {
        this.estadoAtual = estadoAtual;
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
        PropostaDTO that = (PropostaDTO) o;
        return utilizadorId.equals(that.utilizadorId) && organizacaoId.equals(that.organizacaoId) && titulo.equals(that.titulo) && problema.equals(that.problema) && objetivo.equals(that.objetivo) && edicaoUCId.equals(that.edicaoUCId) && estadoAtual == that.estadoAtual;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId, estadoAtual);
    }

    /**
     * Devolve um PropostaDTO com utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId e estadoAtual
     * @return utilizadorId, organizacaoId, titulo, problema, objetivo, edicaoUCId e estadoAtual
     */
    @Override
    public String toString()
    {
        return "PropostaDTO{" +
                "id=" + id +
                ", utilizadorId=" + utilizadorId +
                ", organizacaoId=" + organizacaoId +
                ", titulo='" + titulo + '\'' +
                ", problema='" + problema + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", edicaoUCId=" + edicaoUCId +
                ", estadoAtual=" + estadoAtual +
                '}';
    }
}