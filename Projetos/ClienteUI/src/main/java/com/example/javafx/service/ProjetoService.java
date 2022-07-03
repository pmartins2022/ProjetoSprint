package com.example.javafx.service;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.dto.ConteudoDTO;
import com.example.javafx.dto.ProjetoDTO;
import com.example.javafx.dto.enums.EstadoConteudo;
import com.example.javafx.repository.rest.AvaliacaoRestRepo;
import com.example.javafx.repository.rest.ProjetoRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe service de projeto
 */
@Service
public class ProjetoService
{
    @Autowired
    private ProjetoRestRepository repository;
    @Autowired
    private AvaliacaoRestRepo avaliacaoRestRepo;

    /**
     * Encontrar projetos pelo id do orientador
     * @param id o id
     * @return a lista de projetos
     */
    public List<ProjetoDTO> findAllByOrientadorID(Long id)
    {
        return repository.findAllByOrientadorID(id);
    }

    /**
     * Encontrar conteudos pelo id do projeto
     * @param id o id do projetp
     * @return lista de conteudo
     */
    public List<ConteudoDTO> findAllByIdProjeto(Long id)
    {
        return repository.findAllByIdProjeto(id);
    }

    /**
     * Aceitar um conteudo
     * @param conteudoID o id do conteudo
     */
    public void acceptConteudo(Long conteudoID)
    {
        repository.acceptConteudo(conteudoID);
    }

    /**
     * Rejeitar um conteudo
     * @param conteudoID id do conteudo
     * @return informacao
     */
    public Boolean rejectConteudo(Long conteudoID)
    {
        ConteudoDTO conteudo = repository.rejectConteudo(conteudoID);

        if(!(conteudo.getEstadoConteudo().equals(EstadoConteudo.APROVADO)))
        {
            return false;
        }
        return true;
    }

    /**
     * Criar avaliacao
     * @param avaliacaoDTO avaliacao a criar
     * @return a avaliacao criada
     */
    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO)
    {
        return repository.createAvaliacao(avaliacaoDTO);
    }


    /**
     * Encontrar todas as notas pelo ruc id e pelo estado
     * @param rucID id do ruc
     * @param estado o estado
     * @return a lista de notas
     */
    public List<AvaliacaoNotaDTO> findAllAvaliacaoNotaByRucIDAndEstado(Long rucID, String estado)
    {
        return avaliacaoRestRepo.findAllByEstadoAndRucID(rucID, estado);
    }

    /**
     * Rever uma nota
     * @param id o id da nota
     * @param avaliacao a informacao da avaliacao
     */
    public void reviewAvaliacaoNota(Long id, String avaliacao)
    {
        avaliacaoRestRepo.reviewAvaliacaoNota(id, avaliacao);
    }
}
