package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropostaService
{
    @Autowired
    private PropostaRepository repository;

    @Autowired
    private PropostaDTOMapper mapper;

    public PropostaDTO createProposta(PropostaDTO dto) throws BaseDadosException
    {
        Proposta prop = mapper.toModel(dto);

        Proposta proposta = repository.createProposta(prop);

        return mapper.toDTO(proposta);
    }

    public PropostaDTO rejeitarProposta(Long id) throws IdInvalidoException
    {
        Proposta prop = repository.findById(id);

        prop.reprovarProposta();

        PropostaDTO propostaDTOSaved = atualizarProposta(prop);

        return propostaDTOSaved;
    }

    private PropostaDTO atualizarProposta(Proposta proposta)
    {
        Proposta propostaSaved = repository.atualizarProposta(proposta);

        return mapper.toDTO(propostaSaved);
    }

}