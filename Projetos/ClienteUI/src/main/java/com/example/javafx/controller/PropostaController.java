package com.example.javafx.controller;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.service.PropostaService;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PropostaController
{
    @Autowired
    private PropostaService propostaService;

    public List<String> findAllOrganizacao()
    {
        return propostaService.findAllOrganizacao();
    }

    public List<String> findAllEdicao()
    {
        return propostaService.findAllEdicao();
    }

    public PropostaDTO createProposta(TextField userIdText, int organizacaoId, int edicaoUCId, TextField tituloText, TextField problemaText, TextField objetivoText)
    {
        PropostaDTO dto = new PropostaDTO(Long.parseLong(userIdText.getText()), (long) organizacaoId, (long) edicaoUCId,tituloText.getText(),problemaText.getText(),objetivoText.getText());

        return propostaService.saveProposta(dto);
    }
}
