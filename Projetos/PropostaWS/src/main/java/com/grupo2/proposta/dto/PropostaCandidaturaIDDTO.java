package com.grupo2.proposta.dto;


/**
 * Classe ID de PropostaCandiadturaDTO
 */
public class PropostaCandidaturaIDDTO
{
    /**
     * id de proposta
     */
    private Long idProposta;
    /**
     * id de aluno
     */
    private Long idAluno;

    /**
     * Inicializa PropostaCandidaturaIDDTO sem parametros
     */
    public PropostaCandidaturaIDDTO()
    {
    }

    /**
     * Inicializa idProposta e idAluno de PropostaCandidaturaIDDTO com idProposta e idAluno
     * @param idProposta
     * @param idAluno
     */
    public PropostaCandidaturaIDDTO(Long idProposta, Long idAluno)
    {
        this.idProposta = idProposta;
        this.idAluno = idAluno;
    }

    /**
     * Devolve o idProposta de PropostaCandidaturaIDDTO
     * @return idProposta de PropostaCandidaturaIDDTO
     */
    public Long getIdProposta()
    {
        return idProposta;
    }

    /**
     * Modifica o idProposta de PropostaCandidaturaIDDTO
     * @param idProposta e o novo idProposta
     */
    public void setIdProposta(Long idProposta)
    {
        this.idProposta = idProposta;
    }

    /**
     *  Devolve o idAluno de PropostaCandidaturaIDDTO
     * @return idAluno de PropostaCandidaturaIDDTO
     */
    public Long getIdAluno()
    {
        return idAluno;
    }

    /**
     * Modifica o idProposta de PropostaCandidaturaIDDTO
     * @param idAluno e o novo idAluno
     */
    public void setIdAluno(Long idAluno)
    {
        this.idAluno = idAluno;
    }
}
