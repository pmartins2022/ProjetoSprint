package com.grupo2.anoLetivoWS.repository;

import com.grupo2.anoLetivoWS.exception.ErroGeralException;
import com.grupo2.anoLetivoWS.jpa.AnoLetivoJPA;
import com.grupo2.anoLetivoWS.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.repository.jpa.AnoLetivoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnoLetivoRepository
{
    @Autowired
    private AnoLetivoJPARepository repository;

    @Autowired
    private AnoLetivoJPAMapper mapper;

    public AnoLetivo createAndSaveAnoLetivo(AnoLetivo anoLetivo)
    {
        if (findById(anoLetivo.getSigla()).isPresent())
        {
            throw new ErroGeralException("Ano Letivo já existente.");
        }

        AnoLetivoJPA jpa = mapper.toJpa(anoLetivo);

        AnoLetivoJPA saved = repository.save(jpa);

        return mapper.toModel(saved);
    }

    public Optional<AnoLetivo> findById(String id)
    {
        Optional<AnoLetivoJPA> jpa = repository.findById(id);

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
        List<AnoLetivoJPA> lista = repository.findAll();

        List<AnoLetivo> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }
}
