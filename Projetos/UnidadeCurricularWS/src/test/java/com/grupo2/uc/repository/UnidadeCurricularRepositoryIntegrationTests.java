package com.grupo2.uc.repository;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import com.grupo2.uc.exception.OptionalVazioException;
import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.model.UnidadeCurricular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UnidadeCurricularRepositoryIntegrationTests
{

    @Autowired
    private UnidadeCurricularRepository repository;


    @Test
    public void shouldCreateValidUnidadeCurricular()
    {
        UnidadeCurricular uc = new UnidadeCurricular("PTA", "Português Avançado");

        UnidadeCurricular savedAnoLetivo = repository.saveUnidadeCurricular(uc);

        assertEquals(uc, savedAnoLetivo);
    }

    @Test
    public void shouldNotCreateUnidadeCurricular_Exists()
    {
        UnidadeCurricular uc = new UnidadeCurricular("PTA", "Português Avançado");

        repository.saveUnidadeCurricular(uc);

        assertThrows(ErroGeralException.class, () -> repository.saveUnidadeCurricular(uc));
    }

    @Test
    public void shouldFindAllUnidadeCurricular()
    {
        UnidadeCurricular uc = new UnidadeCurricular("PTA", "Português Avançado");
        UnidadeCurricular uc1 = new UnidadeCurricular("PTAB", "Português Avançado B");

        repository.saveUnidadeCurricular(uc);
        repository.saveUnidadeCurricular(uc1);

        List<UnidadeCurricular> list = new ArrayList<>();
        list.add(uc);
        list.add(uc1);

        List<UnidadeCurricular> all = repository.findAll();

        assertEquals(list, all);
    }


    @Test
    public void shouldFindEmptyListUnidadeCurricular()
    {
        List<UnidadeCurricular> all = repository.findAll();

        assertEquals(List.of(), all);
    }

    @Test
    public void shouldFindUnidadeCurricular_Exists()
    {
        UnidadeCurricular uc = new UnidadeCurricular("PTA", "Português Avançado");

        repository.saveUnidadeCurricular(uc);

        Optional<UnidadeCurricular> found = repository.findBySigla("PTA");

        assertEquals(uc, found.get());
    }

    @Test
    public void shouldNotFindUnidadeCurricular_NotExists()
    {
        Optional<UnidadeCurricular> found = repository.findBySigla("Português");

        assertEquals(Optional.empty(), found);
    }

    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricular ucOld = new UnidadeCurricular("PTA", "Português Deprecated");
        UnidadeCurricular uc = new UnidadeCurricular("PTA", "Português Avançado");

        repository.saveUnidadeCurricular(ucOld);

        UnidadeCurricular unidadeCurricular = repository.updateUnidadeCurricular(uc);

        assertEquals(uc, unidadeCurricular);
    }

    @Test
    public void shouldNotUpdateUnidadeCurricular_DiffSiglaThatNotExists()
    {
        UnidadeCurricular ucInfoUpdate = new UnidadeCurricular("PTAB", "Português Avançado");

        assertThrows(ErroGeralException.class, ()->repository.updateUnidadeCurricular(ucInfoUpdate));
    }

}