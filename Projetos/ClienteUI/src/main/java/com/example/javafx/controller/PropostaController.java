package com.example.javafx.controller;

import com.example.javafx.dto.*;
import com.example.javafx.dto.factory.PropostaDTOFactory;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
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

    private PropostaCandidaturaDTO current;

    /**
     * Buscar a lista de organizações.
     *
     * @return Lista de organizações em formato String.
     */
    public List<OrganizacaoDTO> findAllOrganizacao()
    {
        return propostaService.findAllOrganizacao();
    }

    /**
     * Buscar a lista de edicoes uc.
     *
     * @return Lista de edicoes uc em formato String.
     */
    public List<EdicaoUCDTO> findAllEdicao()
    {
        return propostaService.findAllEdicao();
    }

    /**
     * Criar uma proposta
     *
     * @param userId        id de utilizador.
     * @param organizacaoId id de organização.
     * @param edicaoUCId    id de edicao uc.
     * @param tituloText    titulo da proposta.
     * @param problemaText  problema da proposta.
     * @param objetivoText  objetivo da proposta.
     * @return Proposta criada.
     */
    public PropostaDTO createProposta(Long userId, Long organizacaoId, Long edicaoUCId, String tituloText, String problemaText, String objetivoText)
    {
        PropostaDTO dto = propostaDTOFactory.create(userId, organizacaoId, tituloText, problemaText, objetivoText, edicaoUCId);

        return propostaService.saveProposta(dto);
    }

    /**
     * Encontrar todas as candidaturas a proposta
     * @return a lista de propostas
     */
    public List<PropostaDTO> findAllPropostaCandidatura()
    {
        return propostaService.findAllPropostaByEstadoAtual(0);
    }

    /**
     * Aceitar uma candidatura a proposta
     * @param propostaDTO proposta a aceitar
     * @return informacao de sucesso
     */
    public Boolean acceptCandidaturaProposta(PropostaDTO propostaDTO)
    {
        if (!propostaService.acceptCandidaturaProposta(propostaDTO.getId()))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar a candidatura");
        }

        return true;
    }

    /**
     * Rejeitar uma candidatura a proposta
     * @param propostaDTO proposta a rejeitar
     * @return informacao de sucesso
     */
    public Boolean rejectCandidaturaProposta(PropostaDTO propostaDTO)
    {
        if (!propostaService.rejectCandidaturaProposta(propostaDTO.getId()))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar a candidatura");
        }

        return true;
    }

    /**
     * Encontrar todas as propostas aprovadas
     * @return a lista de propostas
     */
    public List<PropostaDTO> findAllPropostaAprovado()
    {
        return propostaService.findAllPropostaByEstadoAtual(1);
    }

    /**
     * Aceitar uma proposta
     * @param idProposta id da proposta
     * @param alunoID id do aluno
     */
    public void acceptProposta(Long idProposta, Long alunoID)
    {
        propostaService.acceptProposta(idProposta, alunoID);
    }

    /**
     * Rejeitar uma proposta
     * @param idProposta id da proposta
     * @param alunoID id do aluno
     * @return informacao de sucesso
     */
    public Boolean rejectProposta(Long idProposta, Long alunoID)
    {
        if (!propostaService.rejectProposta(idProposta, alunoID))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar Proposta");
        }

        return true;
    }

    /**
     * Encontrar proposta candidatura pelo seu estado e pelo id do aluno
     * @return informacao da proposta
     */
    public PropostaCandidaturaDTO findByEstadoAndAlunoid()
    {
        current = propostaService.findByEstadoAndAlunoid();
        return current;
    }

    /**
     * Encontrar todos os docentes
     * @return lista dos docentes
     */
    public List<UtilizadorDTO> findAllDocente()
    {
        return propostaService.findAllDocente();
    }

    /**
     * Obter a candidatura a proposta atual
     * @return
     */
    public PropostaCandidaturaDTO getCurrent()
    {
        return current;
    }

    /**
     * Criar um novo convite
     * @param dto convite a criar
     * @return informacao do convite criado
     */
    public ConviteDTO createConvite(ConviteDTO dto)
    {
        return propostaService.createConvite(dto);
    }

    /**
     * Obter lista de convites
     * @return lista de convites
     */
    public List<String> getConvites()
    {
        List<ConviteDTO> list = propostaService.getConvites();

        return list.stream().map(ConviteDTO::toString).toList();
    }

    /**
     * Aceitar um convite
     * @param selectedIndex id do convite
     * @return informacao do convite aceite
     */
    public ConviteDTO acceptConvite(int selectedIndex)
    {
        List<ConviteDTO> list = propostaService.getConvites();

        if (selectedIndex < 0 || selectedIndex >= list.size())
        {
            throw new ErrorDetail("Errado",400,"Valor selecionado invalido!");
        }

        return propostaService.acceptConvite(list.get(selectedIndex));
    }

    /**
     * Rejeitar um convite
     * @param selectedIndex id do convite
     * @return informacao do convite rejeitado
     */
    public ConviteDTO rejectConvite(int selectedIndex)
    {
        List<ConviteDTO> list = propostaService.getConvites();

        if (selectedIndex < 0 || selectedIndex >= list.size())
        {
            throw new ErrorDetail("Errado", 400, "Valor selecionado invalido!");
        }

        return propostaService.rejectConvite(list.get(selectedIndex));
    }

    /**
     * Como aluno, candidatar a uma proposta
     * @param propostaID id da proposta
     * @return informacao da candidatura
     */
    public PropostaCandidaturaDTO createAlunoCandidaturaProposta(Long propostaID)
    {
        return propostaService.alunoCandidaturaProposta(propostaID);
    }

    /**
     * Encontrar todos os convites aceites
     * @return a lista de convites aceites
     */
    public List<ConviteDTO> findAllConviteAccepted()
    {
        return propostaService.findAllConviteAccepted();
    }
}

