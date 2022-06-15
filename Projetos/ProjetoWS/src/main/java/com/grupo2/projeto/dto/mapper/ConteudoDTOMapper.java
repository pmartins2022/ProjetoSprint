package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.factory.ConteudoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConteudoDTOMapper
{
    @Autowired
    private ConteudoFactory avaliacaoFactory;

    public Conteudo toModel(ConteudoDTO conteudoDTO)
    {
        return avaliacaoFactory.createConteudo(conteudoDTO.getId(), conteudoDTO.getProjetoId(), conteudoDTO.getTitulo(), conteudoDTO.getCaminhoDocumento(), conteudoDTO.getDocumento(), conteudoDTO.getLinguagemDocumento(), conteudoDTO.getEstadoConteudo());
    }

    public ConteudoDTO toDTO(Conteudo saved)
    {
        return new ConteudoDTO(saved.getId(), saved.getIdProjeto(), saved.getTitulo(), saved.getCaminhoDocumento(), saved.getDocumento(), saved.getLinguagemDocumento(), saved.getEstadoConteudo());
    }
}
