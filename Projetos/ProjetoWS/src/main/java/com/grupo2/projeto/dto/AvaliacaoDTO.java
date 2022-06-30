package com.grupo2.projeto.dto;

import com.grupo2.projeto.model.Conteudo;

public class AvaliacaoDTO
{
    private Long id;

    private Long idMomentoAvaliacao;

    private Long presidenteId;

    private Long orientadorId;

    private Long arguenteId;

    private Long idProjeto;

    private Long idConteudo;
    private String date;


    public AvaliacaoDTO(){}

    public AvaliacaoDTO(Long id, Long idMomentoAvaliacao, Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long idConteudo, String date)
    {
        this.id = id;
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.date = date;
    }

    public AvaliacaoDTO(Long idMomentoAvaliacao,Long presidenteId, Long orientadorId, Long arguenteId, Long idProjeto, Long idConteudo, String date)
    {
        this.idMomentoAvaliacao = idMomentoAvaliacao;
        this.presidenteId = presidenteId;
        this.orientadorId = orientadorId;
        this.arguenteId = arguenteId;
        this.idProjeto = idProjeto;
        this.idConteudo = idConteudo;
        this.date = date;
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

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AvaliacaoDTO{" +
                "id=" + id +
                ", idMomentoAvaliacao=" + idMomentoAvaliacao +
                ", presidenteId=" + presidenteId +
                ", orientadorId=" + orientadorId +
                ", arguenteId=" + arguenteId +
                ", idProjeto=" + idProjeto +
                ", idConteudo=" + idConteudo +
                '}';
    }
}
