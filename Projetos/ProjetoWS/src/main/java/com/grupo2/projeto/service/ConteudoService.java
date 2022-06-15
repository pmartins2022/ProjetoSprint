package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.UtilizadorAuthDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
import com.grupo2.projeto.dto.mapper.ConteudoDTOMapper;
import com.grupo2.projeto.exception.AtualizacaoInvalidaException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.Conteudo;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.repository.ConteudoRepository;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConteudoService
{
    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private ConteudoDTOMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    public ConteudoDTO createAndSave(ConteudoDTO conteudoDTO)
    {
        Conteudo conteudo = mapper.toModel(conteudoDTO);

        Conteudo saved = repository.saveConteudo(conteudo);

        return mapper.toDTO(saved);
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

    public ConteudoDTO acceptConteudo(Long idOrientador, Long idConteudo)
    {
        Optional<UtilizadorDTO> orientador = utilizadorRestRepository.findByOrientadorID(idOrientador);

        if (orientador.isEmpty())
        {
            throw new OptionalVazioException(idOrientador + " não é um Orientador.");
        }

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

        conteudo.get().setEstadoConteudo(EstadoConteudo.APROVADO);

        Conteudo saved = repository.saveConteudo(conteudo.get());

        return mapper.toDTO(saved);
    }

    public Optional<ConteudoDTO> rejeitarConteudo(Long idConteudo)
    {

        UtilizadorAuthDTO current = LoginContext.getCurrent();

        if (current.getTipoUtilizador().equals("DOCENTE"))
        {
            throw new OptionalVazioException(current.getUsername() + " não é um docente.");
        }

        Optional<Conteudo> conteudo = repository.findById(idConteudo);

        if (conteudo.isEmpty())
        {
            return Optional.empty();
        }

        try
        {
            conteudo.get().rejeitarConteudo();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        Optional<ConteudoDTO> saved = atualizarConteudo(conteudo.get());

        if (saved.isEmpty())
        {
            return Optional.empty();
        }

        return saved;
    }

    private Optional<ConteudoDTO> atualizarConteudo(Conteudo conteudo)
    {
        Optional<Conteudo> saved = repository.atualizarConteudo(conteudo);

        if (saved.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toDTO(saved.get()));
    }
}
