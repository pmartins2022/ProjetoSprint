package com.grupo2.organizacao.service;

import com.grupo2.organizacao.dto.NifDTO;
import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.dto.mapper.OrganizacaoDTOMapper;
import com.grupo2.organizacao.exception.ValidacaoInvalidaException;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.repository.OrganizacaoRepository;
import com.grupo2.organizacao.repository.rest.NifRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizacaoService
{
    @Autowired
    private OrganizacaoRepository repository;

    @Autowired
    private OrganizacaoDTOMapper mapper;

    @Autowired
    private NifRestController nifRestController;


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

    public OrganizacaoDTO createAndSave(OrganizacaoDTO dto)
    {
        Optional<NifDTO> nifDTO = nifRestController.findByNif(dto.getNif());

        if (nifDTO.isEmpty()){
            throw new ValidacaoInvalidaException("Nif não existe no Servidor ");
        }

        Organizacao organizacao = mapper.toModel(dto);
        Organizacao saved = repository.save(organizacao);
        OrganizacaoDTO dtoSaved = mapper.toDTO(saved);
        return dtoSaved;
    }

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
}
