package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.ConviteJPA;
import com.grupo2.proposta.model.ConviteID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConviteJPARepository extends JpaRepository<ConviteJPA, ConviteID>
{
    Optional<ConviteJPA> findBy_idProposta_And_idAluno(Long propostaID, Long alunoID);
}