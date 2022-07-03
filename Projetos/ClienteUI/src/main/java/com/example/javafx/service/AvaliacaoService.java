package com.example.javafx.service;

import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.AvaliacaoNotaDTO;
import com.example.javafx.repository.rest.AnoLetivoRestRepo;
import com.example.javafx.repository.rest.AvaliacaoRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service da avaliacoes
 */
@Service
public class AvaliacaoService
{
    @Autowired
    private AvaliacaoRestRepo restRepo;

    /**
     * Encontrar avaliacoes em que o presidente pode criar/alterar nota de avaliacao
     * @return a lista
     */
    public List<AvaliacaoDTO> findEditableAvaliacoes()
    {
        return restRepo.findEditableAvaliacoes();
    }

    /**
     * Encontrar nota pelo id da avaliacao
     * @param id id da avaliacao
     * @return a nota encontrada
     */
    public AvaliacaoNotaDTO findNotaByAvaliacaoID(Long id)
    {
        return restRepo.findNotaByAvaliacaoID(id);
    }

    /**
     * Atualizar uma nota
     * @param notaAtual a nota atual
     */
    public void atualizarNota(AvaliacaoNotaDTO notaAtual)
    {
        restRepo.atualizarNota(notaAtual);
    }

    /**
     * Criar uma nota
     * @param avaliacao a nota a criar
     */
    public void criarNota(AvaliacaoNotaDTO avaliacao)
    {
        restRepo.criarNota(avaliacao);
    }
}
