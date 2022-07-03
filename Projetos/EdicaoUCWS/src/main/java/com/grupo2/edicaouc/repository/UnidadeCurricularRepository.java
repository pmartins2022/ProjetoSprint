package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.exception.ErroGeralException;
import com.grupo2.edicaouc.jpa.UnidadeCurricularJPA;
import com.grupo2.edicaouc.jpa.mapper.UnidadeCurricularJPAMapper;
import com.grupo2.edicaouc.model.UnidadeCurricular;
import com.grupo2.edicaouc.repository.jpa.UnidadeCurricularJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe UnidadeCurricularRepository que permite estabeler ligação com UnidadeCurricularJPARepository
 */
@Repository
public class UnidadeCurricularRepository
{
    /**
     * Objeto do tipo UnidadeCurricularJPARepository a ser utilizado
     */
    @Autowired
    private UnidadeCurricularJPARepository jpaRepository;

    /**
     * Objeto do tipo UnidadeCurricularJPAMapper a ser utilizado
     */
    @Autowired
    private UnidadeCurricularJPAMapper mapper;

    /**
     * Método que permite encontrar UnidadeCurricular pela sigla
     *
     * @param sigla sigla da UnidadeCurricular
     * @return Optional da UnidadeCurricular encontrada ou Optional vazio
     */
    public Optional<UnidadeCurricular> findBySigla(String sigla)
    {
        Optional<UnidadeCurricularJPA> jpa = jpaRepository.findBySigla(sigla);

        if (jpa.isEmpty())
        {
            return Optional.empty();
        }

        UnidadeCurricular unidadeCurricular = mapper.toModel(jpa.get());

        return Optional.of(unidadeCurricular);
    }

    /**
     * Método que permite guardar UnidadeCurricular na Base de Dados
     *
     * @param unidadeCurricular UnidadeCurricular a ser guardada
     * @return UnidadeCurricular guardada
     */
    public UnidadeCurricular saveUnidadeCurricular(UnidadeCurricular unidadeCurricular)
    {
        if (jpaRepository.findById(unidadeCurricular.getSigla()).isPresent())
        {
            throw new ErroGeralException("Unidade Curricular já existente.");
        }

        UnidadeCurricularJPA jpa = mapper.toJPA(unidadeCurricular);

        UnidadeCurricularJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    /**
     * Método que permite atualizar atributos de uma UnidadeCurricular
     *
     * @param uc UnidadeCurricular atualizada
     * @return UnidadeCurricular guardada
     */
    public UnidadeCurricular updateUnidadeCurricular(UnidadeCurricular uc)
    {
        if (jpaRepository.findById(uc.getSigla()).isPresent())
        {
            jpaRepository.deleteById(uc.getSigla());
        } else
        {
            throw new ErroGeralException("O id " + uc.getSigla() + " nao existe.");
        }
        UnidadeCurricularJPA jpa = mapper.toJPA(uc);

        UnidadeCurricularJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    /**
     * Método que permite encontrar todas as UnidadeCurricular's na Base de Dados
     *
     * @return lista com UnidadeCurricular's encontradas
     */
    public List<UnidadeCurricular> findAll()
    {
        List<UnidadeCurricularJPA> lista = jpaRepository.findAll();

        return lista.stream().map(mapper::toModel).toList();
    }

    /**
     * Apaga uma UnidadeCurricular filtrada pela sigla
     * @param sigla sigla da UnidadeCurricular
     */
    public void deleteByID(String sigla)
    {
        jpaRepository.findBySigla(sigla);
    }
}