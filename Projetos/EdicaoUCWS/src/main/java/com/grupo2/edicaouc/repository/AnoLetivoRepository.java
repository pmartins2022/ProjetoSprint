package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.jpa.mapper.AnoLetivoJPAMapper;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.repository.jpa.AnoLetivoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe AnoLetivoRepository que permite estabeler ligação com AnoLetivoJPARepository
 */
@Repository
public class AnoLetivoRepository
{
    @Autowired
    private AnoLetivoJPARepository repository;

    @Autowired
    private AnoLetivoJPAMapper mapper;

    /**
     * Devolve AnoLetivo após persistência
     * @param anoLetivo AnoLetivo a guardar
     * @return AnoLetivo guardado
     */
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

    /**
     * Devolve AnoLetivo filtrado pelo ID ou Optional.Empty()
     * @param id id
     * @return AnoLetivo ou Optional.Empty()
     */
    public Optional<AnoLetivo> findById(String id)
    {
        Optional<AnoLetivoJPA> jpa = repository.findById(id);

        if (jpa.isPresent())
        {
            return jpa.map(mapper::toModel);
        } else
        {
            return Optional.empty();
        }
    }


    /**
     * Devolve Lista de AnoLetivo
     * @return Lista de AnoLetivo
     */
    public List<AnoLetivo> findAll()
    {
        List<AnoLetivoJPA> lista = repository.findAll();

        return lista.stream().map(mapper::toModel).toList();
    }
}
