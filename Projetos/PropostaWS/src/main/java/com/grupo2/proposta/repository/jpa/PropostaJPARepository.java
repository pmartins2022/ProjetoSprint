package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.PropostaJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface que representa o repositorioJpa de PropostaJPA.
 */
public interface PropostaJPARepository extends JpaRepository<PropostaJPA,Long>
{

    List<PropostaJPA> findAllByUtilizadorId(Long id);
    List<PropostaJPA> findAllByTituloContainsIgnoreCase(String titulo);
    List<PropostaJPA> findByorganizacaoId(Long id);

    List<PropostaJPA> findAllByedicaoUCId(Long id);

    List<PropostaJPA> findAllByEstadoAtual(Long i);
}
