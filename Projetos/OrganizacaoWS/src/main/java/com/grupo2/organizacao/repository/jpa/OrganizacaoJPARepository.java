package com.grupo2.organizacao.repository.jpa;

import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizacaoJPARepository extends JpaRepository<OrganizacaoJPA,Long>
{
    Optional<OrganizacaoJPA> findByNIF(Integer nif);
}
