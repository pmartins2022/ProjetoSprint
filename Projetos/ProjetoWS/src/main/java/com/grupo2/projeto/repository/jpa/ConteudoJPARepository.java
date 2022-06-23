package com.grupo2.projeto.repository.jpa;

import com.grupo2.projeto.dataModel.jpa.ConteudoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoJPARepository extends JpaRepository<ConteudoJPA, Long>
{
    List<ConteudoJPA> findAllByIdProjeto(Long id);
}
