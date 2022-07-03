package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Classe AnoLetivoJPARepository que faz extends de JpaRepository
 */
public interface AnoLetivoJPARepository extends JpaRepository<AnoLetivoJPA, String>
{
}
