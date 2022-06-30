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

@Service
public class ProjetoService
{
    @Autowired
    private ProjetoRestRepository repository;
    @Autowired
    private AvaliacaoRestRepo avaliacaoRestRepo;

    public List<ProjetoDTO> findAllByOrientadorID(Long id)
    {
        return repository.findAllByOrientadorID(id);
    }

    public List<ConteudoDTO> findAllByIdProjeto(Long id)
    {
        return repository.findAllByIdProjeto(id);
    }

    public Boolean acceptConteudo(Long conteudoID)
    {
        ConteudoDTO conteudo = repository.acceptConteudo(conteudoID);

        if(!(conteudo.getEstadoConteudo().equals(EstadoConteudo.APROVADO)))
        {
            return false;
        }
        return true;
    }

    public Boolean rejectConteudo(Long conteudoID)
    {
        ConteudoDTO conteudo = repository.rejectConteudo(conteudoID);

        if(!(conteudo.getEstadoConteudo().equals(EstadoConteudo.APROVADO)))
        {
            return false;
        }
        return true;
    }

    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO)
    {
        return repository.createAvaliacao(avaliacaoDTO);
    }


    public List<AvaliacaoNotaDTO> findAllAvaliacaoNotaByRucIDAndEstado(Long rucID, String estado)
    {
        return avaliacaoRestRepo.findAllByEstadoAndRucID(rucID, estado);
    }

    public void reviewAvaliacaoNota(Long id, String avaliacao)
    {
        avaliacaoRestRepo.reviewAvaliacaoNota(id, avaliacao);
    }
}
