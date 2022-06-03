package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.model.factory.PropostaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaDTOMapperUnitTests
{
    @MockBean
    private PropostaFactory factory;

    @InjectMocks
    private PropostaDTOMapper mapper;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldConvertToDTO()
    {
        Proposta prop = mock(Proposta.class);

        when(prop.getId()).thenReturn(1L);
        when(prop.getUtilizadorId()).thenReturn(1L);
        when(prop.getOrganizacaoId()).thenReturn(1L);
        when(prop.getTitulo()).thenReturn("AAA");
        when(prop.getProblema()).thenReturn("AAA");
        when(prop.getObjetivo()).thenReturn("AAA");
        when(prop.getEdicaoUCId()).thenReturn(1L);
        when(prop.getEstadoAtual()).thenReturn(PropostaEstado.REPROVADO);

        PropostaDTO dto = mapper.toDTO(prop);

        assertEquals(prop.getProblema(),dto.getProblema());
        assertEquals(prop.getUtilizadorId(),dto.getUtilizadorId());
        assertEquals(prop.getEstadoAtual(),dto.getEstadoAtual());
    }

    @Test
    public void shouldConvertToModel()
    {
        PropostaDTO prop = mock(PropostaDTO.class);
        Proposta p = mock(Proposta.class);

        when(prop.getId()).thenReturn(1L);
        when(prop.getUtilizadorId()).thenReturn(1L);
        when(prop.getOrganizacaoId()).thenReturn(1L);
        when(prop.getTitulo()).thenReturn("AAA");
        when(prop.getProblema()).thenReturn("AAA");
        when(prop.getObjetivo()).thenReturn("AAA");
        when(prop.getEdicaoUCId()).thenReturn(1L);
        when(prop.getEstadoAtual()).thenReturn(PropostaEstado.REPROVADO);

        when(factory.createProposta(1L,1L,1L,
                "AAA","AAA","AAA",1L,
                PropostaEstado.REPROVADO)).thenReturn(p);

        mapper.toModel(prop);
    }
}