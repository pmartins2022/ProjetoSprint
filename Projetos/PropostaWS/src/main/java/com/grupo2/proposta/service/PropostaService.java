package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
