package com.example.javafx.service;

import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.OrganizacaoDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.dto.factory.EdicaoUCDTOFactory;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.repository.rest.EdicaoUCRestRepo;
import com.example.javafx.repository.rest.OrganizacaoRestRepo;
import com.example.javafx.repository.rest.PropostaRestRepo;
import javafx.beans.binding.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaServiceUnitTests
{
    @MockBean
    OrganizacaoRestRepo organizacaoRestRepo;
    @MockBean
    EdicaoUCRestRepo edicaoUCRestRepo;
    @MockBean
    PropostaRestRepo propostaRestRepo;

    @InjectMocks
    PropostaService service;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllOrganizacao()
    {
        OrganizacaoDTO orgMOCK = mock(OrganizacaoDTO.class);
        List<OrganizacaoDTO> list = List.of(orgMOCK, orgMOCK);

        when(organizacaoRestRepo.findAll()).thenReturn(list);

        List<String> allOrg = service.findAllOrganizacao();

        assertEquals(allOrg.size(), 2);
    }

    @Test
    public void shouldNotFindAllOrganizacao_Empty()
    {
        when(organizacaoRestRepo.findAll()).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.findAllOrganizacao());
    }

    @Test
    public void shouldFindAllEdicao()
    {
        EdicaoUCDTO edicaoMOCK = mock(EdicaoUCDTO.class);
        List<EdicaoUCDTO> list = List.of(edicaoMOCK, edicaoMOCK);
        List<String> stringList = list.stream().map(EdicaoUCDTO::toString).toList();

        when(edicaoUCRestRepo.findAll()).thenReturn(list);

        List<String> allEdicao = service.findAllEdicao();

        assertEquals(allEdicao, stringList);
    }

    @Test
    public void shouldNotFindAllEdicao_Empty()
    {
        when(edicaoUCRestRepo.findAll()).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.findAllEdicao());
    }


    @Test
    public void shouldSaveProposta()
    {
        PropostaDTO propostaMOCK = mock(PropostaDTO.class);

        when(propostaRestRepo.createProposta(propostaMOCK)).thenReturn(propostaMOCK);

        PropostaDTO saved = service.saveProposta(propostaMOCK);

        assertEquals(saved, propostaMOCK);
    }

    @Test
    public void shouldNotSaveProposta_Invalid()
    {
        PropostaDTO propostaMOCK = mock(PropostaDTO.class);

        when(propostaRestRepo.createProposta(propostaMOCK)).thenThrow(RestPostException.class);

        assertThrows(RestPostException.class, () -> service.saveProposta(propostaMOCK));
    }
}