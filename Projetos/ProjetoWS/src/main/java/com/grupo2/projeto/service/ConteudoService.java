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
import com.grupo2.projeto.repository.ConteudoJDBCRepository;
import com.grupo2.projeto.repository.ProjetoJDBCRepository;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Component
public class ConteudoService
{
    @Autowired
    private ConteudoJDBCRepository conteudoJDBCRepository;

    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;
    @Autowired
    private ConteudoDTOMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;


    public void createAndSave(ConteudoDTO conteudoDTO) throws IllegalAccessException
    {
        Projeto projeto = null;
        try
        {
            projeto = projetoJDBCRepository.findById(conteudoDTO.getProjetoId());
        } catch (Exception e)
        {
            throw new IdInvalidoException("Nao existe nenhum projeto com esse id");
        }

        if (!projeto.getEstudanteId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("O aluno nao pertence a esse projeto");
        }

        Conteudo conteudo = mapper.toModel(conteudoDTO);

        try
        {
            conteudoJDBCRepository.insert(conteudo);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public ConteudoDTO findById(Long id)
    {
        Conteudo conteudo = null;
        try
        {
            conteudo = conteudoJDBCRepository.findById(id);
            return mapper.toDTO(conteudo);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Conteúdo com esse ID: "+ id);
        }
    }

    public int acceptConteudo(Long idConteudo)
    {
        Conteudo conteudo = null;
        try
        {
            conteudo = conteudoJDBCRepository.findById(idConteudo);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Conteúdo com esse ID: " + idConteudo);
        }

        if (conteudo.getEstadoConteudo().ordinal() != 0)
        {
            throw new ValidacaoInvalidaException("O conteudo tem que estar PENDENTE" +
                    idConteudo + " encontra-se em fase de " + conteudo.getEstadoConteudo().name());
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
