package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.jpa.mapper.ConviteJPAMapper;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.repository.jpa.ConviteJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
