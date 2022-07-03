package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.MomentoAvaliacaoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe MomentoAvaliacaoJPARepository que faz extends de JpaRepository
 */
public interface MomentoAvaliacaoJPARepository extends JpaRepository<MomentoAvaliacaoJPA, Long>
{
}
