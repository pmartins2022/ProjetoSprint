package com.grupo2.proposta.dto.factory;

import com.grupo2.proposta.dto.ProjetoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProjetoDTOFactory
{
    public ProjetoDTO createProjeto(Long propostaId, Long estudanteId, Long orientadorId)
    {
        return new ProjetoDTO(propostaId,estudanteId,orientadorId);
    }
}