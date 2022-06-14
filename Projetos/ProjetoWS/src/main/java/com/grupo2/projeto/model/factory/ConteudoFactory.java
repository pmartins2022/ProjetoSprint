package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Conteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoFactory
{
    public Conteudo createConteudo(Long id, String titulo, String documento, String linguagem)
    {
        return new Conteudo(id, titulo, documento,linguagem);
    }
}
