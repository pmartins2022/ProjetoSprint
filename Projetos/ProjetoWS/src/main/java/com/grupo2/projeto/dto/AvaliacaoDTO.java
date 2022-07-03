package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.Conteudo;

import java.util.Objects;

public class AvaliacaoDTO
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long presidenteId;

    private Long orientadorId;

    private Long arguenteId;

    private Long idProjeto;

    private Long idConteudo;
    private String estadoAvaliacao;
    private String dataAvaliacao;


    public AvaliacaoDTO(){}

    public AvaliacaoDTO(Long id, Long idMomentoAvaliacao, Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long idConteudo, String estadoAvaliacao, String date)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.estadoAvaliacao = estadoAvaliacao;
        this.dataAvaliacao = date;
    }

    public AvaliacaoDTO(Long idMomentoAvaliacao,Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long idConteudo, String estadoAvaliacao, String date)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.estadoAvaliacao = estadoAvaliacao;
        this.dataAvaliacao = date;
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

    public Long getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(Long idConteudo) {
        this.idConteudo = idConteudo;
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
        return idConteudo;
    }

    public void setConteudo(Long idConteudo)
    {
        this.idConteudo = idConteudo;
    }

    public String getDataAvaliacao()
    {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao)
    {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getEstadoAvaliacao()
    {
        return estadoAvaliacao;
    }

    public void setEstadoAvaliacao(String estadoAvaliacao)
    {
        this.estadoAvaliacao = estadoAvaliacao;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AvaliacaoDTO)) return false;
        AvaliacaoDTO that = (AvaliacaoDTO) o;
        return getIdMomentoAvaliacao().equals(that.getIdMomentoAvaliacao()) && getPresidenteId().equals(that.getPresidenteId()) && getOrientadorId().equals(that.getOrientadorId()) && getArguenteId().equals(that.getArguenteId()) && getIdProjeto().equals(that.getIdProjeto()) && getIdConteudo().equals(that.getIdConteudo()) && getEstadoAvaliacao().equals(that.getEstadoAvaliacao()) && getDataAvaliacao().equals(that.getDataAvaliacao());
    }

    @Override
    public String toString()
    {
        return "AvaliacaoDTO{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", presidenteId=" + presidenteId +
                ", orientadorId=" + orientadorId +
                ", arguenteId=" + arguenteId +
                ", idProjeto=" + idProjeto +
                ", idConteudo=" + idConteudo +
                ", estadoAvaliacao='" + estadoAvaliacao + '\'' +
                ", dataAvaliacao='" + dataAvaliacao + '\'' +
                '}';
    }
}
