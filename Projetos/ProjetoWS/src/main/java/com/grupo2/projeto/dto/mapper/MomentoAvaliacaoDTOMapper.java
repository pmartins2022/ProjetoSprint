package com.grupo2.projeto.dto.mapper;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos MomentoAvaliacao de DTO para classe de dominio.
 */
@Component
public class MomentoAvaliacaoDTOMapper {

    /**
     * O factory a ser utilizado por este DTO Mapper.
     */
    @Autowired
    MomentoAvaliacaoFactory momentoAvaliacaoFactory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param dto objeto dto com os dados
     * @return objeto convertido
     */
    public MomentoAvaliacao toModel(MomentoAvaliacaoDTO dto){
        return momentoAvaliacaoFactory.createMomentoAvaliacao(dto.getId(),dto.getProjetoId(),dto.getPresidenteId(),dto.getOrientadorId(), dto.getArguenteId());
    }

    /**
     * Fazer a conversao para classe DTO
     * @param momentoAvaliacao objeto de dominio com os dados
     * @return objeto convertido
     */
    public MomentoAvaliacaoDTO toDTO(MomentoAvaliacao momentoAvaliacao){
        return new MomentoAvaliacaoDTO(momentoAvaliacao.getId(), momentoAvaliacao.getProjetoId(), momentoAvaliacao.getPresidenteId(), momentoAvaliacao.getOrientadorId(),momentoAvaliacao.getArguenteId());
    }
}
