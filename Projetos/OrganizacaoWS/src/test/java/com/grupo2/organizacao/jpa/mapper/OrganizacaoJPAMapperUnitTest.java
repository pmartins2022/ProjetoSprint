package com.grupo2.organizacao.jpa.mapper;

import com.grupo2.organizacao.exception.ValidacaoInvalidaException;
import com.grupo2.organizacao.jpa.OrganizacaoJPA;
import com.grupo2.organizacao.model.Organizacao;
import com.grupo2.organizacao.model.factory.OrganizacaoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class OrganizacaoJPAMapperUnitTest
{
    @MockBean
    OrganizacaoFactory factory;

    @InjectMocks
    OrganizacaoJPAMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidOrganizacao_ValidAtributtes()
    {
        OrganizacaoJPA organizacaoJPAMOCK = mock(OrganizacaoJPA.class);
        Organizacao organizacaoMOCK = mock(Organizacao.class);

        when(factory.createOrganizacao(organizacaoJPAMOCK.getId(),
                organizacaoJPAMOCK.getDenominacao(), organizacaoJPAMOCK.getNif())).thenReturn(organizacaoMOCK);

        Organizacao organizacao = mapper.toModel(organizacaoJPAMOCK);

        assertEquals(organizacao, organizacaoMOCK);
    }

    @Test
    public void shouldNotConvertValidOrganizacao_InvalidAtributtes()
    {
        OrganizacaoJPA organizacaoJPAMOCK = mock(OrganizacaoJPA.class);
        when(factory.createOrganizacao(organizacaoJPAMOCK.getId(),
                organizacaoJPAMOCK.getDenominacao(), organizacaoJPAMOCK.getNif())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(organizacaoJPAMOCK));
    }

    @Test
    public void shouldConvertValidOrganizacaoJPA_ValidAtributtes()
    {
        OrganizacaoJPA organizacaoJPAMOCK = mock(OrganizacaoJPA.class);
        Organizacao organizacaoMOCK = mock(Organizacao.class);

        when(organizacaoJPAMOCK.getId()).thenReturn(1L);
        when(organizacaoMOCK.getId()).thenReturn(1L);

        OrganizacaoJPA organizacaoJPA = mapper.toJpa(organizacaoMOCK);

        assertEquals(organizacaoJPA.getId(), organizacaoJPAMOCK.getId());
    }

}