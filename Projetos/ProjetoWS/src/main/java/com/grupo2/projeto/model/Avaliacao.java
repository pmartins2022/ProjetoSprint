package com.grupo2.projeto.model;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
import com.grupo2.projeto.model.annotations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Table(tableName = "AVALIACAO")
public class Avaliacao extends JDBCTable
{
    @IgnoreField
    @PrimaryKey( generated = true)
    private Long id;

    @ForeignKey( className = MomentoAvaliacaoDTO.class, fieldName = "ID")
    private Long idMomentoAvaliacao;
    @ForeignKey(className = UtilizadorDTO.class, fieldName = "ID")
    private Long orientadorId;
    @ForeignKey( className = UtilizadorDTO.class, fieldName = "ID")
    private Long presidenteId;

    @ForeignKey(className = UtilizadorDTO.class, fieldName = "ID")
    private Long arguenteId;
    @ForeignKey(className = Projeto.class, fieldName = "ID")
    private Long idProjeto;
    @ForeignKey(className = Conteudo.class, fieldName = "ID")
    private Long conteudo;

    private EstadoAvaliacao estadoAvaliacao;

    @DateConstraint()
    private LocalDate dataAvaliacao;


    public Avaliacao()
    {
    }

    public Avaliacao(Long id, Long idMomentoAvaliacao, Long orientadorId , Long presidenteId, Long arguenteId, Long idProjeto, Long conteudo, String dataAvaliacao)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.estadoAvaliacao = EstadoAvaliacao.PENDENTE;
        this.dataAvaliacao = LocalDate.parse(dataAvaliacao, formatter());
    }

    public Avaliacao(Long idMomentoAvaliacao, Long orientadorId, Long  presidenteId, Long arguenteId, Long idProjeto, Long conteudo, String dataAvaliacao)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.estadoAvaliacao = EstadoAvaliacao.PENDENTE;
        this.dataAvaliacao = LocalDate.parse(dataAvaliacao, formatter());
    }

    public Avaliacao(Long id, Long idMomentoAvaliacao, Long orientadorId, Long  presidenteId, Long arguenteId, Long idProjeto, Long conteudo, String estadoAvaliacao, String dataAvaliacao)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.conteudo = conteudo;
        this.estadoAvaliacao = EstadoAvaliacao.valueOf(estadoAvaliacao);
        this.dataAvaliacao = LocalDate.parse(dataAvaliacao, formatter());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getIdMomentoAvaliacao()
    {
        return idMomentoAvaliacao;
    }

    public void setIdMomentoAvaliacao(Long idMomentoAvaliacao)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
    }

    public Long getPresidenteId() {
        return presidenteId;
    }

    public void setPresidenteId(Long presidenteId) {
        this.presidenteId = presidenteId;
    }

    public Long getOrientadorId() {
        return orientadorId;
    }

    public void setOrientadorId(Long orientadorId) {
        this.orientadorId = orientadorId;
    }

    public Long getArguenteId() {
        return arguenteId;
    }

    public void setArguenteId(Long arguenteId) {
        this.arguenteId = arguenteId;
    }

    public Long getIdProjeto()
    {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto)
    {
        this.idProjeto = idProjeto;
    }

    public Long getConteudo()
    {
        return conteudo;
    }

    public void setConteudo(Long conteudo)
    {
        this.conteudo = conteudo;
    }

    public EstadoAvaliacao getEstadoAvaliacao()
    {
        return estadoAvaliacao;
    }

    public String getNameEstadoAvaliacao()
    {
        return estadoAvaliacao.name();
    }

    public LocalDate getDataAvaliacao()
    {
        return dataAvaliacao;
    }

    public String getDateString()
    {
        return dataAvaliacao.format(formatter());
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao)
    {
        this.dataAvaliacao = dataAvaliacao;
    }

    public void setEstadoAvaliacao(EstadoAvaliacao estadoAvaliacao)
    {
        this.estadoAvaliacao = estadoAvaliacao;
    }



    public DateTimeFormatter formatter()
    {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Override
    public String toString()
    {
        return "Avaliacao{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", orientadorId=" + orientadorId +
                ", presidenteId=" + presidenteId +
                ", arguenteId=" + arguenteId +
                ", idProjeto=" + idProjeto +
                ", conteudo=" + conteudo +
                ", estadoAvaliacao=" + estadoAvaliacao +
                ", dataAvaliacao=" + dataAvaliacao.format(formatter()) +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id) && Objects.equals(idMomentoAvaliacao, avaliacao.idMomentoAvaliacao) && Objects.equals(orientadorId, avaliacao.orientadorId) && Objects.equals(presidenteId, avaliacao.presidenteId) && Objects.equals(arguenteId, avaliacao.arguenteId) && Objects.equals(idProjeto, avaliacao.idProjeto) && Objects.equals(conteudo, avaliacao.conteudo) && estadoAvaliacao == avaliacao.estadoAvaliacao && Objects.equals(dataAvaliacao, avaliacao.dataAvaliacao);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idMomentoAvaliacao, orientadorId, presidenteId, arguenteId, idProjeto, conteudo, estadoAvaliacao, dataAvaliacao);
    }
}
