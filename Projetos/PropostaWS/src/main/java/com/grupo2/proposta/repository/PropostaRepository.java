package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.OrganizacaoDTO;
import com.grupo2.proposta.exception.BaseDadosException;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.jpa.mapper.PropostaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
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
    private PropostaCandidaturaJPAMapper propostaCandidaturaJPAMapper;

    /**
     * Criar uma nova proposta.
     * @param proposta Proposta a ser criada
     * @return Proposta criada
     * @throws BaseDadosException Se ocorrerem erros relativos a base de dados
     */
    public Proposta save(Proposta proposta)
    {
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
        List<PropostaJPA> lista = jpaRepository.findAllByUtilizadorId(id);

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
     * Obter todas as propostas por nif de organizacao.
     * @param dto NIF da organizacao
     * @return Lista de propostas
     */
    public List<Proposta> findByNif(OrganizacaoDTO dto)
    {
        List<PropostaJPA> lista = jpaRepository.findByorganizacaoId(dto.getId());

        List<Proposta> listaModel = lista.stream().map(mapper::toModel).toList();

        return listaModel;
    }

    public List<Proposta> findByEdicaoUCId(Long id)
    {
        List<PropostaJPA> found = jpaRepository.findAllByedicaoUCId(id);

        return found.stream().map(mapper::toModel).toList();
    }

    public List<Proposta> findAllByEstado(Integer estado)
    {
        PropostaEstado value = PropostaEstado.values()[estado];

        List<PropostaJPA> list = jpaRepository.findAllByEstadoAtual(value);

        return list.stream().map(mapper::toModel).toList();
    }

    public Optional<PropostaCandidatura> findPropostaAtivaByAlunoId(Long id, EstadoCandidatura estado)
    {
        Optional <PropostaCandidaturaJPA> propostaCandidaturaJPA = jpaRepository.findByIdIdAlunoAndEstado(id, estado);

        if (propostaCandidaturaJPA.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(propostaCandidaturaJPAMapper.toModel(propostaCandidaturaJPA.get()));
    }
}