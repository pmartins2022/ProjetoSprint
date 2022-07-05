package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.ConviteID;
import org.springframework.stereotype.Component;

@Component
public class ConviteIDFactory
{
    public ConviteID create(Long idAluno, Long idDocente, Long idProposta)
    {
        return new ConviteID(idAluno, idDocente, idProposta);
    }
}
