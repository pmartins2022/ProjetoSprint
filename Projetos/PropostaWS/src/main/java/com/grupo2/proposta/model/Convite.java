package com.grupo2.proposta.model;

/**
 * Entidade que representa uma Convite.
 */
public class Convite
{
    /**
     * id de aluno
     */
    private Long idAluno;
    /**
     * id de docente
     */
    private Long idDocente;
    /**
     * id de proposta
     */
    private Long idProposta;
    /**
     * estado do Convite
     */
    private EstadoConvite estado;

    /**
     * Inicializa idAluno, idDocente, idProposta, estado de Convite com idAluno, idDocente, idProposta, estado
     * @param idAluno e o id do aluno
     * @param idDocente e o id do docente
     * @param idProposta e o id da proposta
     * @param estado e o estado do convite
     */
    public Convite(Long idAluno, Long idDocente, Long idProposta, EstadoConvite estado)
    {
        this.idAluno = idAluno;
        this.idDocente = idDocente;
        this.idProposta = idProposta;
        this.estado = estado;
    }

    /**
     * Devolve o idAluno de Convite
     * @return o idAluno de convite
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     * Modifica o idAluno de Convite
     * @param idAluno e o novo id do aluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    /**
     * Devolve o idDocente de Convite
     * @return o idDocente de convite
     */
    public Long getIdDocente()
    {
        return idDocente;
    }

    /**
     * Modifica o idDocente de Convite
     * @param idDocente e o novo id do docente
     */
    public void setIdDocente(Long idDocente)
    {
        this.idDocente = idDocente;
    }

    /**
     * Devolve o idProposta de Convite
     * @return o idProposta de convite
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     * Modifica o idProposta de Convite
     * @param idProposta e o novo id do proposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     * Devolve o estado de Convite
     * @return o estado de convite
     */
    public EstadoConvite getEstado()
    {
        return estado;
    }

    /**
     * Modifica o estado de Convite
     * @param estado e o novo estado
     */
    public void setEstado(EstadoConvite estado)
    {
        this.estado = estado;
    }
}
