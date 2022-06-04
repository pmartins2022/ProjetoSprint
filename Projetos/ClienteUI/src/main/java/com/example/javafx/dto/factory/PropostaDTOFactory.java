package com.example.javafx.dto.factory;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.PropostaEstado;
import org.springframework.stereotype.Component;

/**
 * Classe factory para criar propostas.
 */
@Component
public class PropostaDTOFactory
{
    /**
     * Criar uma proposta.
     * @param userId Id do utilizador.
     * @param organizacaoId Id da organização.
     * @param titulo Texto do titulo.
     * @param problema Texto do problema.
     * @param objetivo Texto do objetivo.
     * @param edicaoUCId Id da edicao uc.
     * @return Proposta criada.
     */
    public PropostaDTO create(Long userId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId)
    {
        return new PropostaDTO(userId, organizacaoId,edicaoUCId, titulo, problema, objetivo);
    }
}