package com.grupo2.proposta.dto.mapper;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.EstadoConvite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteDTOMapperUnitTests
{
    @Autowired
    private ConviteDTOMapper conviteDTOMapper;

    @Test
    public void shouldConvertToDTO()
    {
        Convite convite = mock(Convite.class);
        when(convite.getIdAluno()).thenReturn(1L);
        when(convite.getIdDocente()).thenReturn(2L);
        when(convite.getIdProposta()).thenReturn(3L);
        when(convite.getEstado()).thenReturn(EstadoConvite.ACEITE);
        ConviteDTO conviteDTO = conviteDTOMapper.toDTO(convite);

        assertEquals(convite.getIdAluno(), conviteDTO.getIdAluno());
        assertEquals(convite.getIdDocente(), conviteDTO.getIdDocente());
        assertEquals(convite.getIdProposta(), conviteDTO.getIdProposta());
        assertEquals(convite.getEstado(), conviteDTO.getEstado());
    }

    @Test
    public void shouldConvertToModel()
    {
        ConviteDTO conviteDTO = new ConviteDTO(1L, 2L, 3L, EstadoConvite.ACEITE);
        Convite convite = conviteDTOMapper.toModel(conviteDTO);

        assertEquals(conviteDTO.getIdAluno(), convite.getIdAluno());
        assertEquals(conviteDTO.getIdDocente(), convite.getIdDocente());
        assertEquals(conviteDTO.getIdProposta(), convite.getIdProposta());
        assertEquals(conviteDTO.getEstado(), convite.getEstado());
    }
}