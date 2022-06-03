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

import java.util.List;
import java.util.Optional;

/**
 * Classe de Service do organizacao. Possui endpoints para createAndSave, findByNif, findALL e findById.
 */
@Service
public class OrganizacaoService
{
    /**
     * O repository a ser utilizado por este Service.
     */
    @Autowired
    private OrganizacaoRepository repository;

    /**
     * O mapper a ser utilizado por este Service.
     */
    @Autowired
    private OrganizacaoDTOMapper mapper;

    /**
     * O RESTController a ser utilizado por este Service.
     */
    @Autowired
    private NifRestController nifRestController;

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
     * Endpoint que possibilita crear e gravar o organizacao existente.
     * @param dto um objeto com os dados
     * @return uma organizacao criada e guardado
     */
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
