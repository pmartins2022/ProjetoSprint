package com.example.javafx.service;

import com.example.javafx.dto.*;
import com.example.javafx.dto.factory.PropostaCandidaturaIDDTOFactory;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.OrganizacaoRestRepo;
import com.example.javafx.repository.rest.PropostaRestRepo;
import com.example.javafx.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service para uma Proposta.
 */
@Service
public class PropostaService
{
    @Autowired
    private OrganizacaoRestRepo repo;

    @Autowired
    private EdicaoUCRestRepo edicaoRepo;

    @Autowired
    private PropostaRestRepo propostaRestRepo;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    @Autowired
    private PropostaCandidaturaIDDTOFactory idFactory;

    /**
     * Obter lista de todas as organizações.
     *
     * @return Lista de organizações.
     */
    public List<OrganizacaoDTO> findAllOrganizacao()
    {
        return repo.findAll();
    }

    /**
     * Obter lista de todas as edições.
     *
     * @return Lista de edições.
     */
    public List<EdicaoUCDTO> findAllEdicao()
    {
        return edicaoRepo.findAll();
    }

    /**
     * Gravar uma nova proposta.
     * @param dto Proposta a ser gravada.
     * @return Proposta gravada.
     * @throws RestPostException Exceção ao gravar a proposta.
     */
    public PropostaDTO saveProposta(PropostaDTO dto) throws RestPostException
    {
        return propostaRestRepo.createProposta(dto);
    }

    public List<PropostaDTO> findAllPropostaByEstadoAtual(Integer estado)
    {
        return propostaRestRepo.findAllPropostaByEstadoAtual(estado);
    }

    public Boolean acceptCandidaturaProposta(Long idProposta)
    {
        PropostaDTO propostaDTO = propostaRestRepo.acceptCandidaturaProposta(idProposta);
        if (propostaDTO.getEstadoAtual() != PropostaEstado.APROVADO)
        {
            return false;
        }

        return true;
    }

    public boolean rejectCandidaturaProposta(Long idProposta)
    {
        PropostaDTO propostaDTO = propostaRestRepo.rejectCandidaturaProposta(idProposta);
        if (propostaDTO.getEstadoAtual() != PropostaEstado.APROVADO)
        {
            return false;
        }

        return true;
    }

    public ProjetoDTO acceptProposta(Long idProposta, Long alunoID)
    {
        ProjetoDTO projetoDTO = propostaRestRepo.acceptProposta(idProposta, alunoID);

        return projetoDTO;
    }

    public boolean rejectProposta(Long idProposta, Long alunoID)
    {
        return propostaRestRepo.rejectProposta(idProposta, alunoID);
    }



    public ConviteDTO createConvite(ConviteDTO conviteDTO)
    {
        ConviteDTO convite = propostaRestRepo.createAndSaveConvite(conviteDTO);

        return convite;
    }

    public PropostaCandidaturaDTO findByEstadoAndAlunoid()
    {
        return propostaRestRepo.findByEstadoAndAlunoid();
    }

    public List<UtilizadorDTO> findAllDocente()
    {
        return utilizadorRestRepository.findAllDocente();
    }

    public List<ConviteDTO> getConvites()
    {
        return propostaRestRepo.getConvites();
    }

    public ConviteDTO acceptConvite(ConviteDTO conviteDTO)
    {
        return propostaRestRepo.aceitarOrientacao(conviteDTO);
    }

    public ConviteDTO rejectConvite(ConviteDTO conviteDTO)
    {
        return propostaRestRepo.rejeitarOrientacao(conviteDTO);
    }

    public PropostaCandidaturaDTO alunoCandidaturaProposta(Long propostaID)
    {
        return propostaRestRepo.alunoCandidaturaProposta(propostaID);
    }
}