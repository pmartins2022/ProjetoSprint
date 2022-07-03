package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.EdicaoUCAlunoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe EdicaoUCAlunoJPARepository que faz extends de JpaRepository
 */
public interface EdicaoUCAlunoJPARepository extends JpaRepository<EdicaoUCAlunoJPA, Long>
{
}
