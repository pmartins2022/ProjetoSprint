package com.grupo2.proposta.model.factory;

import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaEstado;
import org.springframework.stereotype.Component;

/**
 * Classe que cria objetos do tipo Proposta
 */
@Component
public class PropostaFactory
{
    /**
     * Cria uma Proposta
     * @param id Id da Proposta
     * @param utilizadorId Id do Utilizador
     * @param organizacaoId Id da Organizacao
     * @param titulo Titulo da Proposta
     * @param problema Problema da Proposta
     * @param objetivo Objetivo da Proposta
     * @param edicaoUcId Id da Edicao da UC
     * @param estadoAtual Estado Atual da Proposta
     * @return Proposta
     */
    public Proposta createProposta(Long id, Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUcId, PropostaEstado estadoAtual)
    {
        return new Proposta(id, utilizadorId,organizacaoId,titulo,problema,objetivo,edicaoUcId,estadoAtual);
    }


    /**
     * Cria uma Proposta
     * @param utilizadorId Id do Utilizador
     * @param organizacaoId Id da Organizacao
     * @param titulo Titulo da Proposta
     * @param problema Problema da Proposta
     * @param objetivo Objetivo da Proposta
     * @param edicaoUcId Id da Edicao da UC
     * @param estadoAtual Estado Atual da Proposta
     * @return Proposta
     */
    public Proposta createProposta(Long utilizadorId, Long organizacaoId, String titulo, String problema, String objetivo, Long edicaoUcId, PropostaEstado estadoAtual)
    {
        return new Proposta(utilizadorId,organizacaoId,titulo,problema,objetivo,edicaoUcId,estadoAtual);
    }
}