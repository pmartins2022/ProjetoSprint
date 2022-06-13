package com.grupo2.projeto.jpa.mapper;

import com.grupo2.projeto.jpa.MomentoAvaliacaoJPA;
import com.grupo2.projeto.model.MomentoAvaliacao;
import com.grupo2.projeto.model.factory.MomentoAvaliacaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao de objetos Momento de Avaliação de JPA para classe de dominio.
 */
@Component
public class MomentoAvaliacaoJPAMapper {
    /**
     * O factory a ser utilizado por este JPA Mapper
     */
    @Autowired
    private MomentoAvaliacaoFactory momentoAvaliacaoFactory;

    /**
     * Fazer a conversao para classe de dominio
     * @param jpa objeto jpa com os dados
     * @return objeto convertido
     */
    public MomentoAvaliacao toModel(MomentoAvaliacaoJPA jpa)
    {
        return momentoAvaliacaoFactory.createMomentoAvaliacao(jpa.getId(), jpa.getProjetoId(), jpa.getPresidenteId(), jpa.getOrientadorId(), jpa.getArguenteId());
    }

    /**
     * Fazer a conversao para classe JPA
     * @param momento objeto de dominio com os dados
     * @return objeto convertido
     */
    public MomentoAvaliacaoJPA toJPA(MomentoAvaliacao momento)
    {
        return new MomentoAvaliacaoJPA(momento.getId(), momento.getProjetoId(), momento.getPresidenteId(), momento.getOrientadorId(), momento.getArguenteId());
    }
}
