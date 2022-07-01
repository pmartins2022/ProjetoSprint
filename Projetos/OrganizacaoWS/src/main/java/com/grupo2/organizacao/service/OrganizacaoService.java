package com.grupo2.organizacao.service;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.dto.mapper.OrganizacaoDTOMapper;
import com.grupo2.organizacao.exception.ErrorDetail;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.OrganizacaoRepository;
import com.grupo2.organizacao.repository.rest.NifRestRepository;
import com.grupo2.organizacao.repository.rest.ProjetoRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de Service do organizacao.
 */
@Service
public class OrganizacaoService
{

    @Autowired
    private OrganizacaoRepository repository;

    @Autowired
    private OrganizacaoDTOMapper mapper;

    @Autowired
    private NifRestRepository nifRestRepository;

    @Autowired
    private ProjetoRestRepository projetoRestRepository;

    /**
     * Endpoint que possibilita encontrar o organizacao por id existente.
     * @param id um objeto com os dados do id
     * @return uma organizacao
     */
    public Optional<OrganizacaoDTO> findByID(Long id)
    {
        Optional<Organizacao> optionalOrganizacao = repository.findByID(id);

        if (optionalOrganizacao.isPresent())
        {
            OrganizacaoDTO dto = mapper.toDTO(optionalOrganizacao.get());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    /**
     * Endpoint que possibilita criar e gravar o organizacao existente.
     * @param dto um objeto com os dados
     * @return uma organizacao criada e guardado
     */
    public OrganizacaoDTO createAndSave(OrganizacaoDTO dto)
    {
        Optional<NifDTO> nifDTO = nifRestRepository.findByNif(dto.getNif());

        if (nifDTO.isEmpty()){
            throw new OptionalVazioException("Nif não existe no Servidor ");
        }

        dto.setDenominacao(nifDTO.get().getName());

        Organizacao organizacao = mapper.toModel(dto);
        Organizacao saved = repository.save(organizacao);
        OrganizacaoDTO dtoSaved = mapper.toDTO(saved);

        try
        {
            projetoRestRepository.saveOrganizacao(dtoSaved);
        } catch (Exception ignored)
        {
            repository.deleteByID(saved.getId());
        }

        return dtoSaved;
    }

    /**
     * Endpoint que possibilita encontrar o organizacao existente procurada pelo nif.
     * @param nif e um objeto com dados
     * @return uma organizacao
     */
    public Optional<OrganizacaoDTO> findByNIF(Integer nif)
    {
        Optional<Organizacao> optionalOrganizacao = repository.findByNIF(nif);

        if (optionalOrganizacao.isPresent())
        {
            OrganizacaoDTO dto = mapper.toDTO(optionalOrganizacao.get());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    /**
     * Endpoint que possibilita encontrar tadas organizacoes existentes.
     * @return lista de organizacoes
     */
    public List<OrganizacaoDTO> findAll()
    {
        List<Organizacao> list = repository.findAll();

        return list.stream().map(mapper::toDTO).toList();
    }
}
