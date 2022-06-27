package com.grupo2.proposta.repository;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.jpa.mapper.ConviteJPAMapper;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe que gere todas as operações de persistência de Convite.
 */
@Repository
public class ConviteRepository
{
    /**
     * Objeto do tipo ConviteJPARepository a ser utilizador por ConviteRepository
     */
    @Autowired
    private ConviteJPARepository jpaRepository;
    /**
     * Objeto do tipo ConviteJPAMapper a ser utilizador por ConviteRepository
     */
    @Autowired
    private ConviteJPAMapper mapper;

    /**
     * Devolve Convite persistido.
     * @param convite convite a guardar
     * @return Convite
     */
    public Convite createAndSaveConvite(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        return mapper.toModel(jpaRepository.save(conviteJPA));
    }

    /**
     * Devolve Convite filtrado pelo id de proposta e id de aluno ou Optional.emtpy()
     * @param propostaID id de proposta
     * @param alunoID id de aluno
     * @return Convite ou Optional.emtpy()
     */
    public Optional<Convite> findByPropostaAndAluno(Long propostaID, Long alunoID)
    {
        Optional<ConviteJPA> conviteJPA = jpaRepository.findByIdIdpropostaAndIdIdaluno(propostaID, alunoID);

        if (conviteJPA.isPresent())
        {
            return Optional.of(mapper.toModel(conviteJPA.get()));
        }
        return Optional.empty();
    }

    /**
     * Devolve Convite filtrado pelo id de proposta e id de docente ou Optional.emtpy()
     * @param docenteID id de docente
     * @param propostaID id de proposta
     * @return Convite ou Optional.emtpy()
     */
    public Optional<Convite> findByDocenteAndProposta(Long docenteID, Long propostaID)
    {
        Optional<ConviteJPA> conviteJPA = jpaRepository.findByIdIdpropostaAndIdIdaluno(docenteID, propostaID);
        if (conviteJPA.isPresent())
        {
            return Optional.of(mapper.toModel(conviteJPA.get()));
        }
        return Optional.empty();
    }

    /**
     * Devolve Convite filtrado pelo id ou Optional.emtpy()
     * @param id id de Convite
     * @return Convite ou Optional.emtpy()
     */
    public Optional<Convite> findById(ConviteID id)
    {
        Optional<ConviteJPA> id1 = jpaRepository.findById(id);

        if (id1.isPresent())
        {
            return Optional.of(mapper.toModel(id1.get()));
        }
        return Optional.empty();
    }

    /**
     * Invalidar todos os convites de uma proposta que nao sejam do aluno
     *
     * @param propostaID a proposta a ser invalidada
     * @param alunoId    o aluno que nao deve ser invalidado
     */
    public void invalidarConvites(Long propostaID, Long alunoId)
    {
        List<Convite> list = jpaRepository.findByIdIdproposta(propostaID).stream().map(mapper::toModel).toList();

        for (Convite convite : list)
        {
            if (!convite.getIdAluno().equals(alunoId))
            {
                convite.setEstado(EstadoConvite.RECUSADO);
                jpaRepository.save(mapper.toJPA(convite));
            }
        }
    }

    /**
     * Método que invalida todos os Convites com id de proposta enviado por parametro.
     * @param propostaID id de proposta
     */
    public void invalidarTodosConvites(Long propostaID)
    {
        List<Convite> list = jpaRepository.findByIdIdproposta(propostaID).stream().map(mapper::toModel).toList();

        for (Convite convite : list)
        {
            convite.setEstado(EstadoConvite.RECUSADO);
            jpaRepository.save(mapper.toJPA(convite));
        }
    }

    /**
     * Método que atualiza Convite
     * @param convite convite a atualizar
     */
    public void atualizar(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        //jpaRepository.deleteById(conviteJPA.getId());

        jpaRepository.save(conviteJPA);
    }

    /**
     * Devolve Lista de Convite filtrada por id de Docente e em estado PENDENTE
     * @param id id de docente
     * @return Lista de Convite
     */
    public List<Convite> findConvitesPendentes(Long id)
    {
        List<ConviteJPA> list = jpaRepository.findByIdIddocenteAndEstado(id,EstadoConvite.PENDENTE);

        return list.stream().map(mapper::toModel).toList();
    }
}
