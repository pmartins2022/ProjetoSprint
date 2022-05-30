package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.ProjetoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoDTOMapper
{
    @Autowired
    private ProjetoFactory projetoFactory;

    public Projeto toModel(ProjetoDTO dto)
    {
        Projeto projeto = projetoFactory.createProjeto(dto.getId(),dto.getPropostaId(),dto.getEstudanteId(),dto.getOrientadorId());
        return projeto;
    }

    public ProjetoDTO toDTO(Projeto projeto)
    {
        ProjetoDTO projetoDTO = new ProjetoDTO(projeto.getId(), projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId());
        return projetoDTO;
    }
}
