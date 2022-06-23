package com.grupo2.proposta.model;

/**
 * Entidade que representa uma ConviteID.
 */
public class PropostaCandidatura
{
    /**
     * Id da proposta da PropostaCandidatura
     */
    private Long idProposta;
    /**
     * Id do aluno da PropostaCandidatura
     */
    private Long idAluno;
    /**
     * estado da PropostaCandidatura
     */
    private EstadoCandidatura estado;

    /**
     * Inicializa uma PropostaCandidatura
     * @param idProposta id da Proposta da PropostaCandidatura
     * @param idAluno id do Aluno da PropostaCandidatura
     * @param estado estado da PropostaCandidatura
     */
    public PropostaCandidatura(Long idProposta, Long idAluno, EstadoCandidatura estado)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
        this.estado = estado;
    }

    /**
     * Devolve o Id da proposta da PropostaCandidatura
     * @return Id da proposta
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     * Modifica o Id da Proposta da PropostaCandidatura
     * @param idProposta novo Id da Proposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     * Devolce Id do Aluno da PropostaCandidatura
     * @return Id do Aluno
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     * Modifica Id do Aluno da PropostaCandidatura
     * @param idAluno novo Id do Aluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }

    /**
     * Devolve estado da PropostaCandidatura
     * @return estado
     */
    public EstadoCandidatura getEstado()
    {
        return estado;
    }

    /**
     * Modifica estado da PropostaCandidatura
     * @param estado novo estado
     */
    public void setEstado(EstadoCandidatura estado)
    {
        this.estado = estado;
    }
}
