package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * Classe EdicaoUCJpaRepository. Faz extend de JpaRepository
 */
public interface EdicaoUCJpaRepository extends JpaRepository<EdicaoUCJPA, Long>
{
    List<EdicaoUCJPA> findAllByucCode(String code);
}
