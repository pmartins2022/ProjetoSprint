package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.factory.AvaliacaoFactory;
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
        return avaliacaoFactory.createConteudo(conteudoDTO.getId(), conteudoDTO.getTitulo(), conteudoDTO.getDocumento(), conteudoDTO.getLinguagemDocumento());
    }

    public ConteudoDTO toDTO(Conteudo saved)
    {
        return new ConteudoDTO();
    }
}
