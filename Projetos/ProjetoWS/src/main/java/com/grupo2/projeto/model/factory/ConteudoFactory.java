package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoFactory
{
    public Conteudo createConteudo(Long id, Long idProjeto, String titulo, String caminhoDocumento, String documento, String linguagem,String estadoConteudo)
    {
        return new Conteudo(id, idProjeto, titulo, caminhoDocumento,documento,linguagem, estadoConteudo);
    }
}