package com.pp.utilizadorWS.repository.jpa;

import com.pp.utilizadorWS.jpa.UtilizadorJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorJpaRepository extends JpaRepository<UtilizadorJPA, Long>
{
    Optional<UtilizadorJPA> findByUsername(String username);
}