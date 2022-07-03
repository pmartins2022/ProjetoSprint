package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoMomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.MomentoAvaliacaoDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoMomentoAvaliacaoDTOMapper;
import com.grupo2.edicaouc.dto.mapper.MomentoAvaliacaoDTOMapper;
import com.grupo2.edicaouc.exception.OptionalVazioException;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.EdicaoMomentoAvaliacao;
import com.grupo2.edicaouc.model.MomentoAvaliacao;
import com.grupo2.edicaouc.repository.EdicaoMomentoAvaliacaoRepository;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import com.grupo2.edicaouc.repository.MomentoAvaliacaoRepository;
import com.grupo2.edicaouc.repository.rest.ProjetoRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

/**
 Classe de Service de MomentoAvaliacaoService. Permite a ligação entre o MomentoAvaliacaoController e o resto da API
 */
@Service
public class MomentoAvaliacaoService
{
    @Autowired
    private MomentoAvaliacaoRepository repository;

    @Autowired
    private EdicaoUCRepository edicaoUCRepository;

    @Autowired
    private EdicaoMomentoAvaliacaoRepository edicaoMomentoAvaliacaoRepository;

    @Autowired
    private MomentoAvaliacaoDTOMapper mapper;

    @Autowired
    private EdicaoMomentoAvaliacaoDTOMapper edicaoAvaliacaoDTOMapper;

    @Autowired
    private ProjetoRestRepository projetoRestRepository;

    /**
     * Devolve MomentoAvaliacaoDTO após persistência
     * @param dto dto a guardar
     * @return MomentoAvaliacaoDTO guardado
     */
    public MomentoAvaliacaoDTO createAndSaveMomentoAvaliacao(MomentoAvaliacaoDTO dto)
    {
        MomentoAvaliacao save = repository.createAndSaveMomentoAvaliacao(mapper.toModel(dto));

        MomentoAvaliacaoDTO momentoAvaliacaoDTO = mapper.toDTO(save);

        try
        {
            projetoRestRepository.saveMomentoAvaliacao(momentoAvaliacaoDTO);
        } catch (Exception e)
        {
            repository.deleteByID(momentoAvaliacaoDTO.getId());
            throw new ValidacaoInvalidaException("Nao guardou na BD");
        }

        return momentoAvaliacaoDTO;
    }

    /**
     * Devolve EdicaoMomentoAvaliacaoDTO após persistência
     * @param dto EdicaoMomentoAvaliacaoDTO a ser guardado
     * @return EdicaoMomentoAvaliacaoDTO guardado
     */
    public EdicaoMomentoAvaliacaoDTO createAndSaveEdicaoMomentoAvaliacao(EdicaoMomentoAvaliacaoDTO dto)
    {
        if (edicaoUCRepository.findById(dto.getIdEdicao()).isEmpty())
        {
            throw new OptionalVazioException("Nao existe edicao com esse id");
        }

        if (repository.findById(dto.getIdMomentoAvaliacao()).isEmpty())
        {
            throw new OptionalVazioException("Nao existe momento avaliacao com esse id");
        }

        EdicaoMomentoAvaliacao save = edicaoMomentoAvaliacaoRepository.createAndSaveEdicaoMomentoAvaliacao(edicaoAvaliacaoDTOMapper.toModel(dto));

        return edicaoAvaliacaoDTOMapper.toDTO(save);
    }

}
