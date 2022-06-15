package com.example.javafx.controller;

import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.factory.PropostaDTOFactory;
import com.example.javafx.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Classe controller para proposta.
 */
@Controller
public class PropostaController
{
    @Autowired
    private PropostaService propostaService;

    @Autowired
    private PropostaDTOFactory propostaDTOFactory;

    /**
     * Buscar a lista de organizações.
     * @return Lista de organizações em formato String.
     */
    public List<String> findAllOrganizacao()
    {
        return propostaService.findAllOrganizacao();
    }

    /**
     * Buscar a lista de edicoes uc.
     * @return Lista de edicoes uc em formato String.
     */
    public List<String> findAllEdicao()
    {
        return propostaService.findAllEdicao();
    }

    /**
     * Criar uma proposta
     * @param userId id de utilizador.
     * @param organizacaoId id de organização.
     * @param edicaoUCId id de edicao uc.
     * @param tituloText titulo da proposta.
     * @param problemaText problema da proposta.
     * @param objetivoText objetivo da proposta.
     * @return Proposta criada.
     */
    public PropostaDTO createProposta(long userId, int organizacaoId, int edicaoUCId, String tituloText, String problemaText, String objetivoText)
    {
        PropostaDTO dto = propostaDTOFactory.create(userId, (long) organizacaoId, tituloText, problemaText, objetivoText, (long) edicaoUCId);

        return propostaService.saveProposta(dto);
    }

    public List<PropostaDTO> findAllPropostaCandidatura()
    {
        return propostaService.findAllPropostaCandidatura();
    }

    public Boolean acceptCandidaturaProposta(Long idProjeto, Long idAluno)
    {
        propostaService.acceptCandidaturaProposta(idProjeto, idAluno);

        return true;
    }

    public Boolean rejectCandidaturaProposta(Long idProjeto, Long idAluno)
    {
        propostaService.rejectCandidaturaProposta(idProjeto, idAluno);
        return true;
    }
}
