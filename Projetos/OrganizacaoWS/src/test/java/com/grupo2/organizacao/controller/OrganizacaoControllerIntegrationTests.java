package com.grupo2.organizacao.controller;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ListaVaziaException;
import com.grupo2.organizacao.exception.OptionalVazioException;
import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.repository.jpa.OrganizacaoJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrganizacaoControllerIntegrationTests
{

    @Autowired
    OrganizacaoController controller;
    @Autowired
    OrganizacaoJPARepository jpaRepository;

    @Test
    public void shouldFindAllOrganizacao()
    {
        OrganizacaoJPA org =  new OrganizacaoJPA("denominacao", 123456789);
        OrganizacaoJPA org1 = new OrganizacaoJPA("denominacao1", 987654321);

        jpaRepository.save(org);
        jpaRepository.save(org1);

        ResponseEntity<List<OrganizacaoDTO>> responseEntity = controller.findAll();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldFindEmptyListUnidadeCurricular()
    {
        assertThrows(ListaVaziaException.class, ()-> controller.findAll());
    }

    @Test
    public void shouldFindByIDOrganizacao()
    {
        OrganizacaoJPA org =  new OrganizacaoJPA("denominacao", 123456789);

        jpaRepository.save(org);

        ResponseEntity<Object> responseEntity = controller.findByID(org.getId());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void shouldNotFindByIdOrganizacao()
    {
        assertThrows(OptionalVazioException.class, ()-> controller.findByID(123456789L));
    }
}