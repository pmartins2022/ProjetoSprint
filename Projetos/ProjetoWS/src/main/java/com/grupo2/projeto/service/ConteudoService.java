package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.mapper.ConteudoDTOMapper;
import com.grupo2.projeto.exception.AtualizacaoInvalidaException;
import com.grupo2.projeto.exception.IdInvalidoException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.ConteudoRepository;
import com.grupo2.projeto.repository.ProjetoRepository;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConteudoService
{
    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ConteudoDTOMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    public int createAndSave(ConteudoDTO conteudoDTO) throws IllegalAccessException
    {
        Optional<Projeto> id = projetoRepository.findById(conteudoDTO.getProjetoId());
        if (id.isEmpty())
        {
            throw new IdInvalidoException("Nao existe nenhum projeto com esse id");
        }

        if (!id.get().getEstudanteId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("O aluno nao pertence a esse projeto");
        }

        Conteudo conteudo = mapper.toModel(conteudoDTO);

         return repository.saveConteudo(conteudo);

    }

    public Optional<ConteudoDTO> findById(Long id)
    {
        Optional<Conteudo> optional = repository.findById(id);

        if (optional.isPresent())
        {
            ConteudoDTO conteudoDTO = mapper.toDTO(optional.get());
            return Optional.of(conteudoDTO);
        } else
        {
            return Optional.empty();
        }
    }

    public int acceptConteudo(Long idConteudo)
    {
        Optional<Conteudo> conteudo = repository.findById(idConteudo);

        if (conteudo.isEmpty())
        {
            throw new OptionalVazioException("O conteudo com id " + idConteudo + " não existe.");
        }

        if (conteudo.get().getEstadoConteudo().ordinal() != 0)
        {
            throw new ValidacaoInvalidaException("O conteudo tem que estar PENDENTE" +
                    idConteudo + " encontra-se em fase de " + conteudo.get().getEstadoConteudo().name());
        }

        Optional<Projeto> projeto = projetoRepository.findById(conteudo.get().getIdProjeto());

        if (projeto.isEmpty())
        {
            throw new OptionalVazioException("Projeto nao existe");
        }

        if(!projeto.get().getOrientadorId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("Este docente nao e orientador do projeto deste conteudo");
        }

        conteudo.get().setEstadoConteudo(EstadoConteudo.APROVADO);

        return atualizarConteudo(conteudo.get().getId(), conteudo.get().getEstadoConteudo());
    }

    public int rejeitarConteudo(Long idConteudo)
    {
        Optional<Conteudo> conteudo = repository.findById(idConteudo);

        if (conteudo.isEmpty())
        {
            throw new IdInvalidoException("Conteudo com esse id nao existe");
        }

        Optional<Projeto> projeto = projetoRepository.findById(conteudo.get().getIdProjeto());

        if (projeto.isEmpty())
        {
            throw new OptionalVazioException("Projeto nao existe");
        }

        if(!projeto.get().getOrientadorId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("Este docente nao e orientador do projeto deste conteudo");
        }

        try
        {
            conteudo.get().rejeitarConteudo();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        return atualizarConteudo(conteudo.get().getId(), conteudo.get().getEstadoConteudo());
    }

    private int atualizarConteudo(Long id, EstadoConteudo estadoConteudo)
    {
        return repository.atualizarConteudo(id, estadoConteudo);
    }

    public List<ConteudoDTO> findAllByIdProjeto(Long id)
    {
        List<Conteudo> list = repository.findAllByIdProjeto(id);

        return list.stream().map(mapper::toDTO).toList();
    }
}
