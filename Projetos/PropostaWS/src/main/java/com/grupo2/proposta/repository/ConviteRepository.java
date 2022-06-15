package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.ConviteDTO;
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

@Repository
public class ConviteRepository
{
    @Autowired
    private ConviteJPARepository jpaRepository;
    @Autowired
    private ConviteJPAMapper mapper;

    public Convite createAndSaveConvite(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        return mapper.toModel(jpaRepository.save(conviteJPA));
    }

    public Optional<Convite> findByPropostaAndAluno(Long propostaID, Long alunoID)
    {
        Optional<ConviteJPA> conviteJPA = jpaRepository.findByIdIdpropostaAndIdIdaluno(propostaID, alunoID);

        if (conviteJPA.isPresent())
        {
            return Optional.of(mapper.toModel(conviteJPA.get()));
        }
        return Optional.empty();
    }

    public Optional<Convite> findByDocenteAndProposta(Long docenteID, Long propostaID)
    {
        Optional<ConviteJPA> conviteJPA = jpaRepository.findByIdIdpropostaAndIdIdaluno(docenteID, propostaID);
        if (conviteJPA.isPresent())
        {
            return Optional.of(mapper.toModel(conviteJPA.get()));
        }
        return Optional.empty();
    }

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

    public void invalidarTodosConvites(Long propostaID)
    {
        List<Convite> list = jpaRepository.findByIdIdproposta(propostaID).stream().map(mapper::toModel).toList();

        for (Convite convite : list)
        {
            convite.setEstado(EstadoConvite.RECUSADO);
            jpaRepository.save(mapper.toJPA(convite));
        }
    }

    public void atualizar(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        jpaRepository.deleteById(conviteJPA.getId());

        jpaRepository.save(conviteJPA);
    }
}
