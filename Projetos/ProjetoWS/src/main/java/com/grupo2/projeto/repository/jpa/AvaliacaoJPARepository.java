package com.grupo2.projeto.repository.jpa;

import com.grupo2.projeto.dataModel.jpa.AvaliacaoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoJPARepository extends JpaRepository<AvaliacaoJPA, Long>
{
    List<AvaliacaoJPA> findAllByPresidenteId(Long presidenteID);
}
