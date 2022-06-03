package com.grupo2.uc.service;

import com.grupo2.uc.dto.UnidadeCurricularDTO;
import com.grupo2.uc.exception.ErroGeralException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UnidadeCurricularServiceIntegrationTests
{
    @Autowired
    private UnidadeCurricularService service;

    @Test
    public void shouldCreateAndSaveUnidadeCurricular()
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("AABB","CCCCCCCCCC");
        UnidadeCurricularDTO curricular = service.createAndSaveUnidadeCurricular(dto);

        assertNotNull(curricular);
        assertEquals(dto, curricular);
    }

    @Test
    public void shouldNotCreateAndSaveUnidadeCurricular_Duplicate()
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("AABB","CCCCCCCCCC");
        service.createAndSaveUnidadeCurricular(dto);

        UnidadeCurricularDTO dto2 = new UnidadeCurricularDTO("AABB","gsggsgsgsgsgs");

        assertThrows(ErroGeralException.class,()->service.createAndSaveUnidadeCurricular(dto2));
    }

    @Test
    public void shouldFindBySigla()
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("AABB","CCCCCCCCCC");
        service.createAndSaveUnidadeCurricular(dto);
        Optional<UnidadeCurricularDTO> sigla = service.findBySigla("AABB");

        assertTrue(sigla.isPresent());
        assertEquals(dto, sigla.get());
    }

    @Test
    public void shouldNotFindBySigla_NotPresent()
    {
        Optional<UnidadeCurricularDTO> sigla = service.findBySigla("AABB");

        assertFalse(sigla.isPresent());
    }

    @Test
    public void shouldFindAll()
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("AABB","CCCCCCCCCC");
        service.createAndSaveUnidadeCurricular(dto);
        List<UnidadeCurricularDTO> list = service.findAll();

        assertEquals(1, list.size());
    }

    @Test
    public void shouldNotFindAll_Empty()
    {
        List<UnidadeCurricularDTO> list = service.findAll();

        assertEquals(0, list.size());
    }

    @Test
    public void shouldUpdateUnidadeCurricular()
    {
        UnidadeCurricularDTO dto = new UnidadeCurricularDTO("AABB","CCCCCCCCCC");
        service.createAndSaveUnidadeCurricular(dto);

        Optional<UnidadeCurricularDTO> updated = service.update("AABB", "DDDDDDDDDD");

        assertTrue(updated.isPresent());
        assertEquals("DDDDDDDDDD", updated.get().getDenominacao());
    }

    @Test
    public void shouldNotUpdateUnidadeCurricular_NotPresent()
    {
        Optional<UnidadeCurricularDTO> updated = service.update("AABB", "DDDDDDDDDD");

        assertTrue(updated.isEmpty());
    }

}