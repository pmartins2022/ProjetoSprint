package com.example.javafx.dto.factory;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.PropostaEstado;
import org.springframework.stereotype.Component;

@Component
public class PropostaDTOFactory
{
    public PropostaDTO create(Long userId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUCId)
    {
        return new PropostaDTO(userId, organizacaoId,edicaoUCId, titulo, problema, objetivo);
    }
}