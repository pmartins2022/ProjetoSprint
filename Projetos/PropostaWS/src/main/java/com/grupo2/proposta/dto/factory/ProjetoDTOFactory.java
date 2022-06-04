package com.grupo2.proposta.dto.factory;

import com.grupo2.proposta.dto.ProjetoDTO;
import org.springframework.stereotype.Component;

/**
 * Classe que cria objetos do tipo ProjetoDTO
 */
@Component
public class ProjetoDTOFactory
{
    /**
     * Criar um objeto do tipo ProjetoDTO
     * @param propostaId id da proposta
     * @param estudanteId id do estudante
     * @param orientadorId id do orientador
     * @return um objeto do tipo ProjetoDTO
     */
    public ProjetoDTO createProjeto(Long propostaId, Long estudanteId, Long orientadorId)
    {
        return new ProjetoDTO(propostaId,estudanteId,orientadorId);
    }
}