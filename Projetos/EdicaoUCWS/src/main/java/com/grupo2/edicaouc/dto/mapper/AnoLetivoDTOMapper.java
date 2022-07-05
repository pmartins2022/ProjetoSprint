package com.grupo2.edicaouc.dto.mapper;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.model.factory.AnoLetivoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer a conversao entre objetos AnoLetivo de DTO para classe de dominio.
 */
@Component
public class AnoLetivoDTOMapper
{
    @Autowired
    private AnoLetivoFactory anoLetivoFactory;

    /**
     * Fazer a conversao para classe de dominio.
     *
     * @param dto o objeto dto com os dados
     * @return o objeto convertido
     * @throws ValidacaoInvalidaException erro de validacao
     */
    public AnoLetivo toModel(AnoLetivoDTO dto) throws ValidacaoInvalidaException
    {
        AnoLetivo anoLetivo = anoLetivoFactory.createAnoLetivo(dto.getSigla());

        return anoLetivo;
    }

    /**
     * Fazer a conversao para classe DTO
     *
     * @param ano o objeto de dominio com os dados
     * @return o objeto convertido
     */

    public AnoLetivoDTO toDTO(AnoLetivo ano)
    {
        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO(ano.getSigla());

        return anoLetivoDTO;
    }

}