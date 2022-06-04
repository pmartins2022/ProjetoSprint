package com.example.javafx.controller;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.factory.PropostaDTOFactory;
import com.example.javafx.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PropostaController
{
    @Autowired
    private PropostaService propostaService;

    @Autowired
    private PropostaDTOFactory propostaDTOFactory;

    public List<String> findAllOrganizacao()
    {
        return propostaService.findAllOrganizacao();
    }

    public List<String> findAllEdicao()
    {
        return propostaService.findAllEdicao();
    }

    public PropostaDTO createProposta(long userId, int organizacaoId, int edicaoUCId, String tituloText, String problemaText, String objetivoText)
    {
        PropostaDTO dto = propostaDTOFactory.create(userId, (long) organizacaoId,tituloText,problemaText,objetivoText,(long) edicaoUCId);

        return propostaService.saveProposta(dto);
    }
}
