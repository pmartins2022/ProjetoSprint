package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoUCRepositoryIntegrationTests
{
    @Autowired
    private EdicaoUCRepository repository;

    @Autowired
    private EdicaoUCJpaRepository jpaRepository;

    @Autowired
    private EdicaoUCFactory factory;

    @Autowired
    private EdicaoUCJPAMapper mapper;

    @Test
    public void shouldFindByCode()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC(1L, "UC1", "XRYRXVBXBX");
        EdicaoUC edicaoUC2 = factory.createEdicaoUC(2L, "UC1", "FSDDSSDGSDG");
        jpaRepository.save(mapper.toJPA(edicaoUC));
        jpaRepository.save(mapper.toJPA(edicaoUC2));

        List<EdicaoUC> found = repository.findAllEdicaoByUCCode(edicaoUC.getUCCode());

        assertEquals(2, found.size());
    }

    @Test
    public void shouldNotFindByCode_Empty()
    {
        List<EdicaoUC> found = repository.findAllEdicaoByUCCode("PP");
        assertTrue(found.isEmpty());
    }

    @Test
    public void shouldFindById()
    {
        //arrange
        EdicaoUC edicaoUC = factory.createEdicaoUC(1L, "UC1", "XRYRXVBXBX");

        EdicaoUCJPA uc = jpaRepository.save(mapper.toJPA(edicaoUC));
        //act
        Optional<EdicaoUC> id = repository.findById(uc.getId());
        //assert
        assertTrue(id.isPresent());
    }

    @Test
    public void shouldNotFindById()
    {
        Optional<EdicaoUC> id = repository.findById(99L);

        assertTrue(id.isEmpty());
    }
}