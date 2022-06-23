package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.ConviteID;
import org.springframework.stereotype.Component;

/**
 * Classe que cria objetos do tipo ConvitId
 */
@Component
public class ConviteIDFactory
{
    /**
     * Cria um ConviteID
     * @param idAluno e o id do aluno
     * @param idDocente e o id do docente
     * @param idProposta e o id da proposta
     * @return ConviteID
     */
    public ConviteID create(Long idAluno, Long idDocente, Long idProposta)
    {
        return new ConviteID(idAluno, idDocente, idProposta);
    }
}
