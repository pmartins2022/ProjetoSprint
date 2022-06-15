package com.grupo2.projeto.model.factory;

import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import org.springframework.stereotype.Component;

@Component
public class ConteudoFactory
{
    public Conteudo createConteudo(Long id, Long idAluno, String titulo, String caminhoDocumento, String documento, String linguagem, EstadoConteudo estado)
    {
        return new Conteudo(id, idAluno, titulo, caminhoDocumento,documento,linguagem, estado);
    }
}