package com.grupo2.proposta.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade que representa uma ConviteID.
 */
@Embeddable
public class PropostaCandidaturaID implements Serializable
{
    /**
     * Id da Proposta da PropostaCandidaturaID
     */
    private Long idproposta;
    /**
     * Id do Aluno da PropostaCandidaturaID
     */
    private Long idaluno;

    /**
     * Instancia uma PropostaCandidaturaID
     */
    public PropostaCandidaturaID()
    {
    }

    /**
     * Instancia uma PropostaCandidaturaID
     * @param idproposta Id da proposta da PropostaCandidaturaID
     * @param idAluno Id do Aluno da PropostaCandidaturaID
     */
    public PropostaCandidaturaID(Long idproposta, Long idAluno)
    {
        this.idproposta = idproposta;
        this.idaluno = idAluno;
    }

    /**
     * Devolve Id da Proposta da PropostaCandidaturaID
     * @return Id da proposta
     */
    public Long getidproposta()
    {
        return idproposta;
    }

    /**
     * Devolve Id do Aluno da PropostaCandidaturaID
     * @return Id do Aluno
     */
    public Long getIdaluno()
    {
        return idaluno;
    }

    /**
     * Compara dois objetos
     * @param o objeto a ser comparado
     * @return true ou false, conforme o resultado da comparação
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PropostaCandidaturaID)) return false;
        PropostaCandidaturaID that = (PropostaCandidaturaID) o;
        return getidproposta().equals(that.getidproposta()) && getIdaluno().equals(that.getIdaluno());
    }

    /**
     * Devolve o valor hashCode da PropostaCandidaturaID
     * @return valor hashCode
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getidproposta(), getIdaluno());
    }
}
