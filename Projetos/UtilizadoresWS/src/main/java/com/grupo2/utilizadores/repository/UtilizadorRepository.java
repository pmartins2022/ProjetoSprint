package com.grupo2.utilizadores.repository;

import com.grupo2.utilizadores.exception.ErroGeralException;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.jpa.mapper.UtilizadorJPAMapper;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.repository.jpa.UtilizadorJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe de Repository do projeto. Possui endpoints para save e findById.
 */
@Repository
public class UtilizadorRepository
{
    /**
     * O repositoryJPA a ser utilizado por este Repository.
     */
    @Autowired
    private UtilizadorJPARepository jpaRepository;

    /**
     * O repositoryJPAMapper a ser utilizado por este Repository.
     */
    @Autowired
    private UtilizadorJPAMapper mapper;

    /**
     * Endpoint que possibilita encontrar o utilizador por id existente no repositorio.
     * @param id um objeto com os dados do id
     * @return um utilizador
     */
    public Optional<Utilizador> findByID(Long id)
    {
        Optional<UtilizadorJPA> utilizadorJPA = jpaRepository.findById(id);

        if (utilizadorJPA.isEmpty())
        {
            return Optional.empty();
        }

        Utilizador utilizador = mapper.toModel(utilizadorJPA.get());
        return Optional.of(utilizador);
    }

    /**
     * Endpoint que possibilita gravar o utilizador existente no repositorio.
     * @param utilizador um objeto com os dados
     * @return um utilizador guardado
     */
    public Utilizador save(Utilizador utilizador) throws ErroGeralException
    {
        if (jpaRepository.findById(utilizador.getId()).isPresent())
        {
            throw new ErroGeralException("Utilizador com id " + utilizador.getId() + " já existe.");
        }

        UtilizadorJPA jpa = mapper.toJPA(utilizador);

        UtilizadorJPA jpaSaved = jpaRepository.save(jpa);

        Utilizador saved = mapper.toModel(jpaSaved);

        return saved;
    }
}
