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

    public List<PropostaDTO> findAllPropostaCandidatura()
    {
        return propostaService.findAllPropostaByEstadoAtual(0);
    }

    public Boolean acceptCandidaturaProposta(PropostaDTO propostaDTO)
    {
        if (!propostaService.acceptCandidaturaProposta(propostaDTO.getId()))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar a candidatura");
        }

        return true;
    }

    public Boolean rejectCandidaturaProposta(PropostaDTO propostaDTO)
    {
        if (!propostaService.rejectCandidaturaProposta(propostaDTO.getId()))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar a candidatura");
        }

        return true;
    }

    public List<PropostaDTO> findAllPropostaAprovado()
    {
        return propostaService.findAllPropostaByEstadoAtual(1);
    }

    public ProjetoDTO acceptProposta(Long idProposta, String alunoID)
    {
        return propostaService.acceptProposta(idProposta, Long.parseLong(alunoID));
    }

    public Boolean rejectProposta(Long idProposta, String alunoID)
    {
        if (!propostaService.rejectProposta(idProposta, Long.parseLong(alunoID)))
        {
            throw new RestPostException("Ocorreu um erro ao aceitar Proposta");
        }

        return true;
    }

    public PropostaCandidaturaDTO findByEstadoAndAlunoid()
    {
        current = propostaService.findByEstadoAndAlunoid();
        return current;
    }

    public List<UtilizadorDTO> findAllDocente()
    {
        return propostaService.findAllDocente();
    }

    public PropostaCandidaturaDTO getCurrent()
    {
        return current;
    }

    public ConviteDTO createConvite(ConviteDTO dto)
    {
        return propostaService.createConvite(dto);
    }

    public List<String> getConvites()
    {
        List<ConviteDTO> list = propostaService.getConvites();

        return list.stream().map(ConviteDTO::toString).toList();
    }

    public ConviteDTO acceptConvite(int selectedIndex)
    {
        List<ConviteDTO> list = propostaService.getConvites();

        if (selectedIndex < 0 || selectedIndex >= list.size())
        {
            throw new ErrorDetail("Errado",400,"Valor selecionado invalido!");
        }

        return propostaService.acceptConvite(list.get(selectedIndex));
    }

    public ConviteDTO rejectConvite(int selectedIndex)
    {
        List<ConviteDTO> list = propostaService.getConvites();

        if (selectedIndex < 0 || selectedIndex >= list.size())
        {
            throw new ErrorDetail("Errado", 400, "Valor selecionado invalido!");
        }

        return propostaService.rejectConvite(list.get(selectedIndex));
    }

    public PropostaCandidaturaDTO createAlunoCandidaturaProposta(Long propostaID)
    {
        return propostaService.alunoCandidaturaProposta(propostaID);
    }

    public List<AvaliacaoNotaDTO> findAllAvaliacaoNotaByRucIDAndEstado(String estado)
    {
        return propostaService.findAllAvaliacaoNotaByRucIDAndEstado(LoginContext.getCurrentUser().getId(), estado);
    }

    public void reviewAvaliacaoNota(Object selectedItem)
    {

    }

    public void concludeAvaliacaoNota(Object selectedItem)
    {

    }


}

