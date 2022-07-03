package com.grupo2.edicaouc.jpa.mapper;

import com.grupo2.edicaouc.exception.ValidacaoInvalidaException;
import com.grupo2.edicaouc.jpa.AnoLetivoJPA;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.model.factory.AnoLetivoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe para fazer conversao de objetos do tipo AnoLetivo
 */
@Component
public class AnoLetivoJPAMapper
{
    @Autowired
    private AnoLetivoFactory factory;

    /**
     * Converter para um objeto de dominio
     * @param jpa o objeto a converter
     * @return o objeto convertido
     * @throws ValidacaoInvalidaException erro de validacao
     */
    public AnoLetivo toModel(AnoLetivoJPA jpa) throws ValidacaoInvalidaException
    {
        return factory.createAnoLetivo(jpa.getSigla());
    }

    /**
     * Converter para JPA
     * @param model o objeto a converter
     * @return objeto convertido
     */
    public AnoLetivoJPA toJpa(AnoLetivo model)
    {
        return new AnoLetivoJPA(model.getSigla());
    }
}