package com.grupo2.utilizadores.repository.jpa;

import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe UtilizadorJPARepository e faz extend de JpaRepository
 */
public interface UtilizadorJPARepository extends JpaRepository<UtilizadorJPA, Long>
{

}
