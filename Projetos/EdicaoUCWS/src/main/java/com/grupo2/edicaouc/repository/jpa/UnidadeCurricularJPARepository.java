package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.UnidadeCurricularJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe UnidadeCurricularJPARepository que faz extends de JpaRepository
 */
public interface UnidadeCurricularJPARepository extends JpaRepository<UnidadeCurricularJPA, String>
{
    Optional<UnidadeCurricularJPA> findBySigla(String sigla);
}

