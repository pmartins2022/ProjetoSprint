package com.grupo2.uc.repository.jpa;

import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadeCurricularJPARepository extends JpaRepository<UnidadeCurricularJPA, String>
{
    Optional<UnidadeCurricularJPA> findBySigla(String sigla);
}

