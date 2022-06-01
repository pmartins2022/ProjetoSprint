package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EdicaoUCJpaRepository extends JpaRepository<EdicaoUCJPA, Long>
{
    Optional<EdicaoUCJPA> findByucCode(String code);
    List<EdicaoUCJPA> findAllByucCode(String code);
    Optional<EdicaoUCJPA> findByanoLetivoCode(String code);


}
