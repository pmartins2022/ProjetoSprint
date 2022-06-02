package com.grupo2.projeto.repository.jpa;

import com.grupo2.projeto.jpa.ProjetoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe ProjetoJPARepository e faz extend de JpaRepository
 */
public interface ProjetoJPARepository extends JpaRepository<ProjetoJPA, Long>
{
}
