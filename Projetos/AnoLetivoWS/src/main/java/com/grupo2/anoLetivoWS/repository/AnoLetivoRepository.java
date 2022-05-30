package com.grupo2.anoLetivoWS.repository;

import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJpa;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.repository.jpa.AnoLetivoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnoLetivoRepository
{
    @Autowired

    private AnoLetivoJPARepository repository;

    @Autowired
    private AnoLetivoJPAMapper mapper;

    public AnoLetivo saveAnoLetivo(AnoLetivo anoLetivo)
    {
        if (findById(anoLetivo.getSigla()).isPresent())
        {
            throw new ErroGeralException("Ano Letivo já existente.");
        }

        AnoLetivoJpa jpa = mapper.toJpa(anoLetivo);

        AnoLetivoJpa saved = repository.save(jpa);

        return mapper.toModel(saved);
    }

    public Optional<AnoLetivo> findById(String id)
    {
        Optional<AnoLetivoJpa> jpa = repository.findById(id);

        if (jpa.isPresent())
        {
            return jpa.map(mapper::toModel);
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<AnoLetivo> findAll()
    {
        List<AnoLetivoJpa> lista = repository.findAll();

        List<AnoLetivo> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }
}
