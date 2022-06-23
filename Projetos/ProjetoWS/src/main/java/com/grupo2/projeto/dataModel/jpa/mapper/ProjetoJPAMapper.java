package com.grupo2.projeto.dataModel.jpa.mapper;

import com.grupo2.projeto.dataModel.jpa.ProjetoJPA;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.model.factory.ProjetoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos Projeto de JPA para classe de dominio.
 */
@Component
public class ProjetoJPAMapper
{
    /**
     *  O factory a ser utilizado por este JPA Mapper.
     */
    @Autowired
    private ProjetoFactory factory;

    /**
     * Fazer a conversao para classe de dominio.
     * @param jpa o objeto jpa com os dados
     * @return o objeto convertido
     */
    public Projeto toModel(ProjetoJPA jpa)
    {
        return factory.createProjeto(jpa.getId(),jpa.getPropostaId(),jpa.getEstudanteId(),jpa.getOrientadorId());
    }

    /**
     * Fazer a conversao para classe JPA
     * @param projeto o objeto de dominio com os dados
     * @return o objeto convertido
     */
    public ProjetoJPA toJpa(Projeto projeto)
    {
        return new ProjetoJPA(projeto.getId(), projeto.getPropostaId(), projeto.getEstudanteId(), projeto.getOrientadorId());
    }
}
