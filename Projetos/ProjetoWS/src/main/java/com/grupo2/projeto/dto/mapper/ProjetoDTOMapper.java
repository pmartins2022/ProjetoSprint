package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.ProjetoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos Projeto de DTO para classe de dominio.
 */
@Component
public class ProjetoDTOMapper
{
    @Autowired
    private ProjetoFactory projetoFactory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     */
    public Projeto toModel(ProjetoDTO dto)
    {
        return projetoFactory.createProjeto(dto.getId(),dto.getPropostaId(),dto.getEstudanteId(),dto.getOrientadorId());
    }

    /**
     * Fazer a conversao para classe DTO
     * @param projeto o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public ProjetoDTO toDTO(Projeto projeto)
    {
        return new ProjetoDTO(projeto.getId(), projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId());
    }
}
