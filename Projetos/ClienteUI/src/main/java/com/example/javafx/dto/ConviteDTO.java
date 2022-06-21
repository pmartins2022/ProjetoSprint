package com.example.javafx.dto;

import com.example.javafx.model.EstadoConvite;

import java.util.Objects;

/**
 * Classe DTO que contem informacao sobre um convite.
 */
public class ConviteDTO
{
    private Long idAluno;
    private Long idDocente;
    private Long idProposta;

    private EstadoConvite estado;

    /**
     * Metodo que inicializa um ConvitDTO sem parametros
     */
    public ConviteDTO(){}

    /**
     * Metodo que inicializa um ConvitDTO
     * @param idAluno é o id do aluno
     * @param idDocente é o id do docente
     * @param idProposta é o id da proposta
     * @param estado é o estado da proposta
     */
    public ConviteDTO(Long idAluno, Long idDocente, Long idProposta, EstadoConvite estado)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
        this.estado = estado;
    }

    /**
     * Metodo que inicializa um ConvitDTO
     * @param idAluno é o id do aluno
     * @param idDocente é o aluno do docente
     * @param idProposta é o id da proposta
     */
    public ConviteDTO(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
    }

    /**
     *
     * @return
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     *
     * @return
     */
    public Long getIdDocente()
    {
        return idDocente;
    }

    /**
     *
     * @return
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     *
     * @param idAluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    /**
     *
     * @param idDocente
     */
    public void setIdDocente(Long idDocente)
    {
        this.idDocente = idDocente;
    }

    /**
     *
     * @param idProposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     *
     * @return
     */
    public EstadoConvite getEstado()
    {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(EstadoConvite estado)
    {
        this.estado = estado;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConviteDTO that = (ConviteDTO) o;
        return idDocente.equals(that.idDocente) && idProposta.equals(that.idProposta) && estado == that.estado;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(idAluno, idDocente, idProposta, estado);
    }

    @Override
    public String toString()
    {
        return "ConviteDTO{" +
                "idAluno=" + idAluno +
                ", idDocente=" + idDocente +
                ", idProposta=" + idProposta +
                ", estado=" + estado +
                '}';
    }
}
