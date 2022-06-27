package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface que representa o repositorioJpa de PropostaCandidaturaJPA.
 */
public interface PropostaCandidaturaJPARepository extends JpaRepository<PropostaCandidaturaJPA, PropostaCandidaturaID>
{
    Optional<PropostaCandidaturaJPA> findByIdIdalunoAndEstado(Long id, EstadoCandidatura est);

}