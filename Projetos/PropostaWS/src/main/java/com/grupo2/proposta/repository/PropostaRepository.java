package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.EdicaoUCDTO;
import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.dto.UtilizadorDTO;
import com.grupo2.proposta.exception.BaseDadosException;
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

/**
 * Classe que gere todas as operações de persistência de Proposta e de comunicacao com os repositorios externos para
 * obtenção / validacao de dados.
 */
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

    /**
     * Criar uma nova proposta. Necessita de validar os dados de utilizador e edicaoUC externamente.
     * @param proposta Proposta a ser criada
     * @return Proposta criada
     * @throws BaseDadosException Se ocorrerem erros relativos a base de dados
     */
    public Proposta createProposta(Proposta proposta) throws BaseDadosException
    {
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

    /**
     * Obter uma proposta pelo id.
     * @param id Id da proposta
     * @return Proposta ou optional vazio
     */
    public Optional<Proposta> findById(Long id)
    {
        Optional<PropostaJPA> jpa = jpaRepository.findById(id);

        if (jpa.isEmpty()) return Optional.empty();

        return Optional.of(mapper.toModel(jpa.get()));
    }

    /**
     * Atualiza uma proposta.
     * @param proposta Proposta a ser atualizada
     * @return Proposta atualizada ou optional vazio
     */
    public Optional<Proposta> atualizarProposta(Proposta proposta)
    {
        if (jpaRepository.findById(proposta.getId()).isPresent())
        {
            jpaRepository.deleteById(proposta.getId());

            PropostaJPA jpa = mapper.toJPA(proposta);

            PropostaJPA saved = jpaRepository.save(jpa);

            return Optional.of(mapper.toModel(saved));
        }
        else
        {
            return Optional.empty();
        }
    }

    /**
     * Obter todas as propostas por id de utilizador.
     * @param id Id do utilizador
     * @return Lista de propostas
     */
    public List<Proposta> findByIdUtilizador(Long id)
    {
        List<PropostaJPA> lista = jpaRepository.findAllByutilizadorId(id);

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }

    /**
     * Obter todas as propostas por titulo
     * @param titulo Titulo da proposta
     * @return Lista de propostas
     */
    public List<Proposta> findAllByTitulo(String titulo)
    {
        List<PropostaJPA> lista = jpaRepository.findAllByTituloContainsIgnoreCase(titulo);

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }

    /**
     * Obter todas as propostas por id de organizacao.
     * @param nif NIF da organizacao
     * @return Lista de propostas
     */
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