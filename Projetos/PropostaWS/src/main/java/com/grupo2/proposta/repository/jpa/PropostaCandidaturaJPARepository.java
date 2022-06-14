package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaCandidaturaJPARepository extends JpaRepository<PropostaCandidaturaJPA, PropostaCandidaturaID>
{

}