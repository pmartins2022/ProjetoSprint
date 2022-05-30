package com.grupo2.anoLetivoWS.dto.mapper;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.exception.ValidacaoInvalidaException;
import com.grupo2.anoLetivoWS.model.AnoLetivo;
import com.grupo2.anoLetivoWS.model.factory.AnoLetivoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnoLetivoDTOMapper
{
    @Autowired
    private AnoLetivoFactory anoLetivoFactory;

    public AnoLetivo toModel(AnoLetivoDTO dto) throws ValidacaoInvalidaException
    {
        return anoLetivoFactory.createAnoLetivo(dto.getSigla());
    }

    public AnoLetivoDTO toDTO(AnoLetivo ano)
    {
        return new AnoLetivoDTO(ano.getSigla());
    }

}