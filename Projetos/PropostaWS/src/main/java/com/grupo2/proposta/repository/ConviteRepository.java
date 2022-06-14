package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.jpa.mapper.ConviteJPAMapper;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Optional<ConviteJPA> conviteJPA = jpaRepository.findByDocenteAndProposta(docenteID,propostaID);
        if (conviteJPA.isPresent())
        {
            return Optional.of(mapper.toModel(conviteJPA.get()));
        }
        return Optional.empty();
    }

    public void atualizar(Convite convite)
    {
        ConviteJPA conviteJPA = mapper.toJPA(convite);

        jpaRepository.deleteById(conviteJPA.getId());

        jpaRepository.save(conviteJPA);
    }

}
