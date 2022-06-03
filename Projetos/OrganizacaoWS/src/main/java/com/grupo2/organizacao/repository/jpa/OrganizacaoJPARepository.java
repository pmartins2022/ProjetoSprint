package com.grupo2.organizacao.repository.jpa;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe OrganizacaoJPARepository e faz extend de JpaRepository
 */
public interface OrganizacaoJPARepository extends JpaRepository<OrganizacaoJPA,Long>
{
    /**
     * Encontrar uma organizacao pelo seu nif
     * @param nif e o nif
     * @return de um optional da OrganizacaoJPA
     */
    Optional<OrganizacaoJPA> findBynif(Integer nif);
}
