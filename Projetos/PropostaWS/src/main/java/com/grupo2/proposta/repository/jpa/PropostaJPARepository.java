package com.grupo2.proposta.repository.jpa;

import com.grupo2.proposta.jpa.PropostaJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaJPARepository extends JpaRepository<PropostaJPA,Long>
{

    List<PropostaJPA> findAllByutilizadorId(Long id);
    List<PropostaJPA> findAllByTitulo(String titulo);

    List<PropostaJPA> findByorganizacaoId(Long id);
}
