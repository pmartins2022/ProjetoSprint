package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaJPAMapper;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRepository
{
    @Autowired
    private PropostaJPARepository jpaRepository;

    @Autowired
    private PropostaJPAMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    @Autowired
    private EdicaoUCRestRepository edicaoUCRestRepository;

    @Autowired
    private OrganizacaoRestRepository organizacaoRestRepository;

    public Proposta createProposta(Proposta proposta) throws BaseDadosException
    {
        System.out.println("Find by id "+proposta.getUtilizadorId());
        Optional<UtilizadorDTO> utilizadorId = utilizadorRestRepository.findById(proposta.getUtilizadorId());

        if (utilizadorId.isEmpty())
        {
            throw new BaseDadosException("Id de utilizador "+proposta.getUtilizadorId()+" nao existe.");
        }

        Optional<OrganizacaoDTO> organizacaoId = organizacaoRestRepository.findById(proposta.getOrganizacaoId());

        if (organizacaoId.isEmpty())
        {
            throw new BaseDadosException("Id de organizacao "+proposta.getOrganizacaoId()+" nao existe.");
        }

        Optional<EdicaoUCDTO> edicaoId = edicaoUCRestRepository.findById(proposta.getEdicaoUCId());

        if (edicaoId.isEmpty())
        {
            throw new BaseDadosException("Id de edicao unidade curricular "+proposta.getEdicaoUCId()+" nao existe.");
        }

        PropostaJPA jpa = mapper.toJPA(proposta);

        PropostaJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    public Optional<Proposta> findById(Long id)
    {
        Optional<PropostaJPA> jpa = jpaRepository.findById(id);

        if (jpa.isEmpty()) return Optional.empty();

        return Optional.of(mapper.toModel(jpa.get()));
    }

    public Proposta atualizarProposta(Proposta proposta)
    {
        jpaRepository.deleteById(proposta.getId());

        PropostaJPA jpa = mapper.toJPA(proposta);

        PropostaJPA saved = jpaRepository.save(jpa);

        return mapper.toModel(saved);
    }

    public List<Proposta> findByIdUtilizador(Long id)
    {
        List<PropostaJPA> lista = jpaRepository.findAllByutilizadorId(id);

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }
    public List<Proposta> findAllByTitulo(String titulo)
    {
        List<PropostaJPA> lista = jpaRepository.findAllByTitulo(titulo);

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }

    public List<Proposta> findByNif(Integer nif)
    {
        Optional<OrganizacaoDTO> dto = organizacaoRestRepository.findByNIF(nif);

        if (dto.isEmpty())
        {
            return List.of();
        }

        List<PropostaJPA> lista = jpaRepository.findByorganizacaoId(dto.get().getId());

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }
}