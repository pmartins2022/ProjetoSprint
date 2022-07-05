package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.jpa.EdicaoUCJPA;
import com.grupo2.edicaouc.jpa.mapper.EdicaoUCJPAMapper;
import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.EstadoEdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import com.grupo2.edicaouc.repository.jpa.EdicaoUCJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
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
        EdicaoUC edicaoUC = factory.createEdicaoUC(1L, "UC1", "XRYRXVBXBX",1L);
        EdicaoUC edicaoUC2 = factory.createEdicaoUC(2L, "UC1", "FSDDSSDGSDG",1L);
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
        EdicaoUC edicaoUC = factory.createEdicaoUC(1L, "UC1", "XRYRXVBXBX",1L);

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

    @Test
    public void shouldAtivarEdicao()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC( "UC1", "XRYRXVBXBX",1L);

        assertSame(edicaoUC.getEstadoEdicaoUC(), EstadoEdicaoUC.PENDENTE);

        EdicaoUCJPA save = jpaRepository.save(mapper.toJPA(edicaoUC));

        EdicaoUC alterar = mapper.toModel(save);

        alterar.activateEdicaoUC();

        EdicaoUC uc = repository.ativarEdicao(alterar);

        assertSame(uc.getEstadoEdicaoUC(), EstadoEdicaoUC.ATIVA);

        assertEquals(uc,alterar);
    }

    @Test
    public void shouldDesativarEdicao()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC( "UC1", "XRYRXVBXBX",1L);

        assertSame(edicaoUC.getEstadoEdicaoUC(), EstadoEdicaoUC.PENDENTE);

        EdicaoUCJPA save = jpaRepository.save(mapper.toJPA(edicaoUC));

        EdicaoUC alterar = mapper.toModel(save);

        alterar.deactivateEdicaoUC();

        EdicaoUC uc = repository.desativarEdicao(alterar);

        assertSame(uc.getEstadoEdicaoUC(), EstadoEdicaoUC.DESATIVA);

        assertEquals(uc,alterar);
    }

    @Test
    public void shouldFindAll()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC( "UC1", "XRYRXVBXBX",1L);
        EdicaoUC edicaoUC2 = factory.createEdicaoUC( "UC2", "XRYRXVBXBX",1L);
        jpaRepository.save(mapper.toJPA(edicaoUC));
        jpaRepository.save(mapper.toJPA(edicaoUC2));

        List<EdicaoUC> found = repository.findAll();

        assertEquals(2, found.size());
    }

    @Test
    public void shouldSaveEdicaoUC()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC( "UC1", "XRYRXVBXBX",1L);

        EdicaoUC uc = repository.saveEdicaoUC(edicaoUC);

        assertEquals(uc,edicaoUC);
    }

    @Test
    public void shouldNotSaveEdicaoUC_duplicate()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC( "UC1", "2000-2001",1L);

        repository.saveEdicaoUC(edicaoUC);

        repository.saveEdicaoUC(edicaoUC);

        assertThrows(DataIntegrityViolationException.class,()->repository.findAll().size());
    }
}