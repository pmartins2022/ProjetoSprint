package com.grupo2.edicaouc.service;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.mapper.EdicaoUCDTOMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.repository.EdicaoUCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdicaoUCService
{
    @Autowired
    private EdicaoUCRepository repository;

    @Autowired
    private EdicaoUCDTOMapper mapper;


    public List<EdicaoUCDTO> findAllEdicaoByUCCode(String ucCode ) {

        List<EdicaoUC> opEdicaoUC = repository.findAllEdicaoByUCCode(ucCode);

        return opEdicaoUC.stream().map(mapper::toDTO).toList();
    }


    public EdicaoUCDTO createEdicaoUC(String ucCode, String anoLetivoCode)
    {
        EdicaoUCDTO dto = new EdicaoUCDTO(ucCode,anoLetivoCode);

        EdicaoUC edicaoUC = mapper.toModel(dto);

        EdicaoUC saveEdicaoUC = repository.saveEdicaoUC(edicaoUC);

        return mapper.toDTO(saveEdicaoUC);
    }

    public Optional<EdicaoUCDTO> findById(Long id)
    {
        Optional<EdicaoUC> edicao = repository.findById(id);

        if (edicao.isPresent())
        {
            return Optional.of(mapper.toDTO(edicao.get()));
        }

        return Optional.empty();
    }
}
