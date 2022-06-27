package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.ConviteID;
import com.grupo2.proposta.model.EstadoConvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Interface que representa o repositorioJpa de ConviteJPA.
 */
public interface ConviteJPARepository extends JpaRepository<ConviteJPA, ConviteID>
{

    Optional<ConviteJPA> findByIdIdpropostaAndIdIdaluno(Long propostaId, Long idaluno);

    List<ConviteJPA> findByIdIdproposta(Long propostaID);

    List<ConviteJPA> findByIdIddocenteAndEstado(Long iddocente, EstadoConvite pendente);
}