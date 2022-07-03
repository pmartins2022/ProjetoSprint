package com.example.javafx.service;

import com.example.javafx.dto.*;
import com.example.javafx.dto.factory.PropostaCandidaturaIDDTOFactory;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.*;
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
    private AvaliacaoRestRepo avaliacaoRestRepo;
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

    /**
     * Devolve Lista de PropostaDTO filtradas pelo estado
     * @param estado estado
     * @return Lista de PropostaDTO
     */
    public List<PropostaDTO> findAllPropostaByEstadoAtual(Integer estado)
    {
        return propostaRestRepo.findAllPropostaByEstadoAtual(estado);
    }

    /**
     * Devolve true ou false caso tenha tido sucesso em aceitar a candidatura a Proposta
     * @param idProposta id de proposta
     * @return boolean
     */
    public Boolean acceptCandidaturaProposta(Long idProposta)
    {
        PropostaDTO propostaDTO = propostaRestRepo.acceptCandidaturaProposta(idProposta);
        if (propostaDTO.getEstadoAtual() != PropostaEstado.APROVADO)
        {
            return false;
        }

        return true;
    }

    /**
     * Devolve true ou false caso tenha tido sucesso em rejeitar a candidatura a Proposta
     * @param idProposta id de proposta
     * @return boolean
     */
    public boolean rejectCandidaturaProposta(Long idProposta)
    {
        PropostaDTO propostaDTO = propostaRestRepo.rejectCandidaturaProposta(idProposta);
        if (propostaDTO.getEstadoAtual() != PropostaEstado.APROVADO)
        {
            return false;
        }

        return true;
    }

    /**
     * Método que aceita Proposta
     * @param idProposta id de Proposta
     * @param alunoID id de Aluno
     */
    public void acceptProposta(Long idProposta, Long alunoID)
    {
        propostaRestRepo.acceptProposta(idProposta, alunoID);
    }

    /**
     * Método que rejeita Proposta
     * @param idProposta id de Proposta
     * @param alunoID id de Aluno
     */
    public boolean rejectProposta(Long idProposta, Long alunoID)
    {
        return propostaRestRepo.rejectProposta(idProposta, alunoID);
    }

    /**
     * Método que cria Convite
     * @param conviteDTO convite
     * @return ConviteDTO
     */
    public ConviteDTO createConvite(ConviteDTO conviteDTO)
    {
        ConviteDTO convite = propostaRestRepo.createAndSaveConvite(conviteDTO);

        return convite;
    }

    /**
     * Devolve PropostaCandidaturaDTO filtrado pelo Estado e AlunoID
     * @return
     */
    public PropostaCandidaturaDTO findByEstadoAndAlunoid()
    {
        return propostaRestRepo.findByEstadoAndAlunoid();
    }

    /**
     * Encontrar todos os docentes
     * @return lista de docentes
     */
    public List<UtilizadorDTO> findAllDocente()
    {
        return utilizadorRestRepository.findAllDocente();
    }

    /**
     * Encontrar todos os convites
     * @return lista de convites
     */
    public List<ConviteDTO> getConvites()
    {
        return propostaRestRepo.getConvites();
    }

    /**
     * Encontrar todos os convites aceites
     * @return lista de convites
     */
    public List<ConviteDTO> findAllConviteAccepted()
    {
        return propostaRestRepo.findAllConviteAccepted();
    }

    /**
     * Aceitar convite
     * @param conviteDTO o convite a aceitar
     * @return o convite aceite
     */
    public ConviteDTO acceptConvite(ConviteDTO conviteDTO)
    {
        return propostaRestRepo.aceitarOrientacao(conviteDTO);
    }

    /**
     * Rejeitar convite
     * @param conviteDTO o convite a rejeitar
     * @return o convite rejeitado
     */
    public ConviteDTO rejectConvite(ConviteDTO conviteDTO)
    {
        return propostaRestRepo.rejeitarOrientacao(conviteDTO);
    }

    /**
     * Candidatar a uma proposta
     * @param propostaID o id da proposta
     * @return candidatura do aluno
     */
    public PropostaCandidaturaDTO alunoCandidaturaProposta(Long propostaID)
    {
        return propostaRestRepo.alunoCandidaturaProposta(propostaID);
    }
}