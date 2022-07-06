package com.grupo2.projeto.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AvaliacaoDTO
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long orientadorId;
    private Long presidenteId;

    private Long arguenteId;

    private Long idProjeto;

    private Long idConteudo;
    private String estadoAvaliacao;
    private String dataAvaliacao;


    public AvaliacaoDTO(){}

    public AvaliacaoDTO(Long id, Long idMomentoAvaliacao, Long orientadorId, Long presidenteId, Long arguenteId, Long idProjeto, Long idConteudo, String estadoAvaliacao, String date)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.estadoAvaliacao = estadoAvaliacao;
        this.dataAvaliacao = LocalDate.parse(date, formatter()).format(formatter());
    }

    public AvaliacaoDTO(Long idMomentoAvaliacao, Long orientadorId, Long presidenteId, Long arguenteId, Long idProjeto, Long idConteudo, String estadoAvaliacao, String date)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.estadoAvaliacao = estadoAvaliacao;
        this.dataAvaliacao = LocalDate.parse(date, formatter()).format(formatter());
    }

    public DateTimeFormatter formatter()
    {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
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

    public Long getOrientadorId()
    {
        return orientadorId;
    }

    public void setOrientadorId(Long orientadorId)
    {
        this.orientadorId = orientadorId;
    }

    public Long getPresidenteId()
    {
        return presidenteId;
    }

    public void setPresidenteId(Long presidenteId)
    {
        this.presidenteId = presidenteId;
    }

    public Long getArguenteId()
    {
        return arguenteId;
    }

    public void setArguenteId(Long arguenteId)
    {
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

    public Long getIdConteudo()
    {
        return idConteudo;
    }

    public void setIdConteudo(Long idConteudo)
    {
        this.idConteudo = idConteudo;
    }

    public String getEstadoAvaliacao()
    {
        return estadoAvaliacao;
    }

    public void setEstadoAvaliacao(String estadoAvaliacao)
    {
        this.estadoAvaliacao = estadoAvaliacao;
    }

    public String getDataAvaliacao()
    {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao)
    {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getDateString()
    {
        return LocalDate.parse(dataAvaliacao, formatter()).format(formatter());
    }

    @Override
    public String toString()
    {
        return "AvaliacaoDTO{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", orientadorId=" + orientadorId +
                ", presidenteId=" + presidenteId +
                ", arguenteId=" + arguenteId +
                ", idProjeto=" + idProjeto +
                ", idConteudo=" + idConteudo +
                ", estadoAvaliacao='" + estadoAvaliacao + '\'' +
                ", dataAvaliacao='" + dataAvaliacao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoDTO that = (AvaliacaoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(idMomentoAvaliacao, that.idMomentoAvaliacao) && Objects.equals(orientadorId, that.orientadorId) && Objects.equals(presidenteId, that.presidenteId) && Objects.equals(arguenteId, that.arguenteId) && Objects.equals(idProjeto, that.idProjeto) && Objects.equals(idConteudo, that.idConteudo) && Objects.equals(estadoAvaliacao, that.estadoAvaliacao) && Objects.equals(dataAvaliacao, that.dataAvaliacao);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, idMomentoAvaliacao, orientadorId, presidenteId, arguenteId, idProjeto, idConteudo, estadoAvaliacao, dataAvaliacao);
    }
}
