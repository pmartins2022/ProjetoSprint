package com.grupo2.organizacao.model.factory;

import com.grupo2.organizacao.model.Organizacao;
import org.springframework.stereotype.Component;

/**
 * Classe Factory da Organizacao
 */
@Component
public class OrganizacaoFactory
{
    /**
     * Cria objetos do tipo organizacao
     * @param id recebe o id dos mappers
     * @param denominacao recebe o denominacao dos mappers
     * @param nif recebe o nif dos mappers
     * @return um novo objeto do tipo Organizacao
     */
    public Organizacao createOrganizacao(Long id, String denominacao, Integer nif)
    {
        return new Organizacao(id,denominacao,nif);
    }
}
