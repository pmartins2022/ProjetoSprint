package com.grupo2.proposta.dto;

import com.grupo2.proposta.model.EstadoCandidatura;

import java.util.Objects;

public class PropostaCandidaturaDTO
{
    private Long idProposta;
    private Long idAluno;
    private EstadoCandidatura estado;

    /**
     * Inicializa PropostaCandidaturaDTO sem parametros
     */
    public PropostaCandidaturaDTO()
    {
    }

    /**
     * Inicializa idProposta, idAluno, estado de PropostaCandidaturaDTO com idProposta, idAluno, estado
     * @param idProposta e idProposta de PropostaCandidaturaDTO
     * @param idAluno e idAluno de PropostaCandidaturaDTO
     * @param estado e estado de PropostaCandidaturaDTO
     */
    public PropostaCandidaturaDTO(Long idProposta, Long idAluno, EstadoCandidatura estado)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
        this.estado = estado;
    }

    /**
     * Devolve o idProposta de PropostaCandidaturaDTO
     * @return idProposta de PropostaCandidaturaDTO
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     *  Modifica o idProposta de PropostaCandidaturaDTO
     * @param idProposta e a novo idProposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     * Devolve o idAluno de PropostaCandidaturaDTO
     * @return idAluno de PropostaCandidaturaDTO
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     * Modifica o idAluno de PropostaCandidaturaDTO
     * @param idAluno e a novo idAluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    /**
     * Devolve o estado de PropostaCandidaturaDTO
     * @return estado de PropostaCandidaturaDTO
     */
    public EstadoCandidatura getEstado()
    {
        return estado;
    }

    /**
     * Modifica o estado de PropostaCandidaturaDTO
     * @param estado e a novo estado
     */
    public void setEstado(EstadoCandidatura estado)
    {
        this.estado = estado;
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
        PropostaCandidaturaDTO that = (PropostaCandidaturaDTO) o;
        return idProposta.equals(that.idProposta) && idAluno.equals(that.idAluno) && estado == that.estado;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(idProposta, idAluno, estado);
    }

    /**
     * Devolve um PropostaCandidaturaDTO com idProposta, idAluno e estado
     * @return idProposta, idAluno e estado
     */
    @Override
    public String toString()
    {
        return "PropostaCandidaturaDTO{" +
                "idProposta=" + idProposta +
                ", idAluno=" + idAluno +
                ", estado=" + estado +
                '}';
    }
}
