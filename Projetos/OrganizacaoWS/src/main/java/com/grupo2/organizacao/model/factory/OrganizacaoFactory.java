package com.grupo2.organizacao.model.factory;

import com.grupo2.organizacao.model.Organizacao;
import org.springframework.stereotype.Component;

@Component
public class OrganizacaoFactory
{
    public Organizacao createOrganizacao(Long id, String denominacao, Integer nif)
    {
        return new Organizacao(id,denominacao,nif);
    }
}
