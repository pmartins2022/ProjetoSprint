package com.example.javafx.dto.factory;

import com.example.javafx.dto.AvaliacaoDTO;
import org.springframework.stereotype.Component;

/**
 * Classe factory para criar objetos do tipo AvaliacaoDTO
 */
@Component
public class AvaliacaoDTOFactory
{

    /**
     * Criar uma avaliacao
     * @param idMomentoAvaliacao id do momento
     * @param idOrientador id do orientador
     * @param idPresidente id do presidente
     * @param idArguente id do arguente
     * @param idProjeto id do projeto
     * @param idConteudo id do conteudo
     * @return a avaliacao criada
     */
    public AvaliacaoDTO create(Long idMomentoAvaliacao, Long idOrientador, Long idPresidente,
                               Long idArguente, Long idProjeto, Long idConteudo)
    {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(idMomentoAvaliacao, idOrientador, idPresidente, idArguente, idProjeto, idConteudo);
        System.out.println(avaliacaoDTO);
        return avaliacaoDTO;
    }
}
