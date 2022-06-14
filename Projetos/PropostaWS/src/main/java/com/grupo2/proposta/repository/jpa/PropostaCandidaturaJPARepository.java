package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.PropostaInscricaoJPA;
import com.grupo2.proposta.model.PropostaInscricaoID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaCandidaturaJPARepository extends JpaRepository<PropostaInscricaoJPA, PropostaInscricaoID>
{

}