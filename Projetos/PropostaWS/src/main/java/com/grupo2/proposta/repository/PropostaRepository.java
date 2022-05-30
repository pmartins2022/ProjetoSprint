package com.grupo2.proposta.repository;

import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PropostaRepository
{
    @Autowired
    private PropostaJPARepository jpaRepository;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    public void save()
    {
        utilizadorRestRepository.findById();
    }
}
