package com.grupo2.projeto.repository.jpa;

import com.grupo2.projeto.dataModel.jpa.ProjetoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Classe ProjetoJPARepository e faz extend de JpaRepository
 */
public interface ProjetoJPARepository extends JpaRepository<ProjetoJPA, Long>
{
    List<ProjetoJPA> findAllByOrientadorId(Long id);
}
