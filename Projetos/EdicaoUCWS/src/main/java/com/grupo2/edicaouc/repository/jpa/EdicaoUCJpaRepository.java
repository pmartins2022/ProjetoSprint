package com.grupo2.edicaouc.repository.jpa;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe EdicaoUCJpaRepository. Faz extend de JpaRepository
 */
public interface EdicaoUCJpaRepository extends JpaRepository<EdicaoUCJPA, Long>
{
    /**
     * Encontrar todas as edicoes a partir do UC Code
     * @param code uc code a procurar
     * @return lista
     */
    List<EdicaoUCJPA> findAllByucCode(String code);

    /**
     * Encontrar lista de edicoes a partir do ruc ID
     * @param rucID id a procurar
     * @return lista
     */
    List<EdicaoUCJPA> findByRucID(Long rucID);

    /**
     * Encontrar edicao de um certo RUC e do seu Estado
     * @param rucID o id do ruc
     * @param estado o estado
     * @return o objeto encontrado
     */
    Optional<EdicaoUCJPA> findByRucIDAndEstadoEdicaoUC(Long rucID, EstadoEdicaoUC estado);

    /**
     * Encontrar edicao a partir de um estado
     * @param ativa estado a procurar
     * @return o objeto encontrado
     */
    Optional<EdicaoUCJPA> findByEstadoEdicaoUC(EstadoEdicaoUC ativa);
}
