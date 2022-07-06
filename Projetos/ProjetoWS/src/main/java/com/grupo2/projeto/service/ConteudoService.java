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
            throw new OptionalVazioException("Não existe Conteúdo com esse ID: " + id);
        }
    }

    public void acceptConteudo(Long idConteudo) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
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

        Projeto projeto = null;
        try
        {
            projeto = projetoJDBCRepository.findById(conteudo.getIdProjeto());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Projeto nao existe");
        }

        if (!projeto.getOrientadorId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("Este docente nao e orientador do projeto deste conteudo");
        }

        conteudo.setEstadoConteudo(EstadoConteudo.APROVADO);

        atualizarConteudo(conteudo);
    }

    public void rejeitarConteudo(Long idConteudo)
    {
        Conteudo conteudo = null;
        try
        {
            conteudo = conteudoJDBCRepository.findById(idConteudo);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Conteudo nao existe");
        }

        Projeto projeto = null;
        try
        {
            projeto = projetoJDBCRepository.findById(conteudo.getIdProjeto());
        }
        catch (Exception e)
        {
            throw new OptionalVazioException("Projeto nao existe");
        }

        if (!projeto.getOrientadorId().equals(LoginContext.getCurrent().getId()))
        {
            throw new IdInvalidoException("Este docente nao e orientador do projeto deste conteudo");
        }

        try
        {
            conteudo.rejeitarConteudo();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        atualizarConteudo(conteudo);
    }

    private void atualizarConteudo(Conteudo cont)
    {
        conteudoJDBCRepository.update(cont);
    }

    public List<ConteudoDTO> findAllByIdProjeto(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        List<Conteudo> list = conteudoJDBCRepository.findAllByIdProjeto(id);

        return list.stream().map(mapper::toDTO).toList();
    }
}
