package com.grupo2.edicaouc.repository;

import com.grupo2.edicaouc.model.EdicaoUC;
import com.grupo2.edicaouc.model.factory.EdicaoUCFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoUCRepositoryIntegrationTests
{
    @Autowired
    private EdicaoUCRepository repository;

    @Autowired
    private EdicaoUCFactory factory;

    @Test
    public void shouldFindByCode()
    {
        EdicaoUC edicaoUC = factory.createEdicaoUC(1L, "UC1", "XRYRXVBXBX");
        EdicaoUC edicaoUC2 = factory.createEdicaoUC(2L, "UC1", "FSDDSSDGSDG");
        repository.saveEdicaoUC(edicaoUC);
        repository.saveEdicaoUC(edicaoUC2);

        List<EdicaoUC> found = repository.findAllEdicaoByUCCode(edicaoUC.getUCCode());

        assertEquals(2, found.size());
    }

    @Test
    public void shouldNotFindByCode_Empty()
    {
        List<EdicaoUC> found = repository.findAllEdicaoByUCCode("PP");
        assertTrue(found.isEmpty());
    }
}