package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.EstadoConvite;

import java.util.Objects;

/**
 * Classe DTO que contem informacao sobre um convite.
 */
public class ConviteDTO
{
    /**
     * Id do aluno
     */
    private Long idAluno;
    /**
     * Id do docente
     */
    private Long idDocente;
    /**
     * Id da proposta
     */
    private Long idProposta;

    /**
     * Estado do Convite
     */
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
     * Devolve o id do aluno
     * @return id do aluno
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     * Devolve o id do docente
     * @return o id do docente
     */
    public Long getIdDocente()
    {
        return idDocente;
    }

    /**
     * Devolve o id da proposta
     * @return o id da proposta
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     *  Modifica o id do aluno
     * @param idAluno o novo id do aluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    /**
     * Modifica o id do docente
     * @param idDocente o novo id do docente
     */
    public void setIdDocente(Long idDocente)
    {
        this.idDocente = idDocente;
    }

    /**
     * Modifica o id da proposta
     * @param idProposta novo id da proposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     * Devolve o estado do convite
     * @return o estado do convite
     */
    public EstadoConvite getEstado()
    {
        return estado;
    }

    /**
     * Modifica o estado do convite
     * @param estado o novo estado do convite
     */
    public void setEstado(EstadoConvite estado)
    {
        this.estado = estado;
    }

    /**
     * Compara dois objetos do tipo ConviteDTO
     * @param o e um objeto do tipo ConviteDTO
     * @return true se forem iguais e false se nao forem iguais
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConviteDTO that = (ConviteDTO) o;
        return idDocente.equals(that.idDocente) && idProposta.equals(that.idProposta) && estado == that.estado;
    }

    /**
     * {{@code @Inherit}}
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(idAluno, idDocente, idProposta, estado);
    }
}
