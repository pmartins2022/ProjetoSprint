package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Classe EdicaoUCJpaRepository. Faz extend de JpaRepository
 */
public interface EdicaoUCJpaRepository extends JpaRepository<EdicaoUCJPA, Long>
{
    List<EdicaoUCJPA> findAllByucCode(String code);

    /*@Query(value = "select count(*) from EdicaoUC ed " +
            " inner join EdicaoUCAluno edUC on edUC.ucCode = ed.edicaoUCID where ed.estado = 1 and edUC.id.alunoID = (:idAluno)", nativeQuery = true)
    Integer count(@Param("idAluno") Long idAluno);*/

}
