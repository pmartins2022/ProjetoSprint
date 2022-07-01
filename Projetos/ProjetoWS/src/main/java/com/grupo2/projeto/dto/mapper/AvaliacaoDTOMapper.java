package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.factory.AvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoDTOMapper
{
    @Autowired
    private AvaliacaoFactory avaliacaoFactory;

    @Autowired
    private ConteudoDTOMapper mapper;

    public Avaliacao toModel(AvaliacaoDTO avaliacaoDTO)
    {
        return avaliacaoFactory.createAvaliacao(avaliacaoDTO.getId(),avaliacaoDTO.getIdMomentoAvaliacao(),
                avaliacaoDTO.getPresidenteId(), avaliacaoDTO.getOrientadorId(), avaliacaoDTO.getArguenteId(), avaliacaoDTO.getArguenteId(), avaliacaoDTO.getConteudo(),avaliacaoDTO.getDataAvaliacao());
    }

    public AvaliacaoDTO toDTO(Avaliacao avaliacao)
    {
        return new AvaliacaoDTO(avaliacao.getId(),avaliacao.getIdProjeto(),
                avaliacao.getIdMomentoAvaliacao(), avaliacao.getPresidenteId(), avaliacao.getOrientadorId(), avaliacao.getArguenteId(), avaliacao.getConteudo(), avaliacao.getEstadoAvaliacao().name(), avaliacao.getDateString());
    }
}
