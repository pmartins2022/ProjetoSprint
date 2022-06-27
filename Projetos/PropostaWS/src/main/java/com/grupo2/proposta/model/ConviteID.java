package com.grupo2.proposta.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade que representa uma ConviteID.
 */
@Embeddable
public class ConviteID implements Serializable
{
    /**
     * id de aluno
     */
    private Long idaluno;
    /**
     * id de docente
     */
    private Long iddocente;
    /**
     * id de proposta
     */
    private Long idproposta;

    /**
     * Inicializa ConvitID sem parametros
     */
    public ConviteID()
    {
    }

    /**
     * Inicializa idAluno, idDocente, idProposta de ConvitID com idAluno, idDocente, idProposta
     * @param idAluno e o id do aluno
     * @param idDocente e o id do docente
     * @param idProposta e o id da proposta
     */
    public ConviteID(Long idAluno, Long idDocente, Long idProposta)
    {
        this.idaluno = idAluno;
        this.iddocente = idDocente;
        this.idproposta = idProposta;
    }

    /**
     * Devolve o idAluno de ConviteID
     * @return o idAluno de ConviteID
     */
    public Long getIdaluno()
    {
        return idaluno;
    }

    /**
     * Devolve o iddocente de ConviteID
     * @return o iddocente de ConviteID
     */
    public Long getIddocente()
    {
        return iddocente;
    }

    /**
     * Devolve o idproposta de ConviteID
     * @return o idproposta de ConviteID
     */
    public Long getIdproposta()
    {
        return idproposta;
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
        if (!(o instanceof ConviteID)) return false;
        ConviteID conviteID = (ConviteID) o;
        return getIdaluno().equals(conviteID.getIdaluno()) && getIddocente().equals(conviteID.getIddocente()) && getIdproposta().equals(conviteID.getIdproposta());
    }


    /**
     * Devolve valor hashcode do objeto
     * @return valor hashcode
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getIdaluno(), getIddocente(), getIdproposta());
    }
}
