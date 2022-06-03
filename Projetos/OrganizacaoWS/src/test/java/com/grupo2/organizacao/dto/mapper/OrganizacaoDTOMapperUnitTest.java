package com.grupo2.organizacao.dto.mapper;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ValidacaoInvalidaException;
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
class OrganizacaoDTOMapperUnitTest
{
    @MockBean
    OrganizacaoFactory factory;

    @InjectMocks
    OrganizacaoDTOMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldConvertValidOrganizacao_ValidAtributtes()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);
        Organizacao organizacaoMOCK = mock(Organizacao.class);

        when(factory.createOrganizacao(organizacaoDTOMOCK.getId(),
                organizacaoDTOMOCK.getDenominacao(), organizacaoDTOMOCK.getNif())).thenReturn(organizacaoMOCK);

        Organizacao organizacao = mapper.toModel(organizacaoDTOMOCK);

        assertEquals(organizacao, organizacaoMOCK);
    }

    @Test
    public void shouldNotConvertValidOrganizacao_InvalidAtributtes()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);

        when(factory.createOrganizacao(organizacaoDTOMOCK.getId(),
                organizacaoDTOMOCK.getDenominacao(), organizacaoDTOMOCK.getNif())).thenThrow(ValidacaoInvalidaException.class);

        assertThrows(ValidacaoInvalidaException.class, () -> mapper.toModel(organizacaoDTOMOCK));
    }

    @Test
    public void shouldConvertValidOrganizacaoJPA_ValidAtributtes()
    {
        OrganizacaoDTO organizacaoDTOMOCK = mock(OrganizacaoDTO.class);
        Organizacao organizacaoMOCK = mock(Organizacao.class);

        when(organizacaoDTOMOCK.getId()).thenReturn(1L);
        when(organizacaoMOCK.getId()).thenReturn(1L);

        OrganizacaoDTO organizacaoDTO = mapper.toDTO(organizacaoMOCK);

        assertEquals(organizacaoDTO.getId(), organizacaoDTOMOCK.getId());
    }
}