package com.grupo2.proposta.repository;

import com.grupo2.proposta.dto.PropostaCandidaturaDTO;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.model.factory.PropostaCandidaturaIDFactory;
import com.grupo2.proposta.model.factory.PropostaCandidaturaJPAFactory;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Classe que gere todas as operações de persistência de PropostaCandidatura.
 */
@Repository
public class PropostaCandidaturaRepo
{
    /**
     * Objeto do tipo PropostaCandidaturaJPARepository a ser utilizador por PropostaCandidaturaRepo
     */
    @Autowired
    private PropostaCandidaturaJPARepository jpaRepository;
    /**
     * Objeto do tipo PropostaCandidaturaJPAMapper a ser utilizador por PropostaCandidaturaRepo
     */
    @Autowired
    private PropostaCandidaturaJPAMapper mapper;
    /**
     * Objeto do tipo PropostaCandidaturaJPAFactory a ser utilizador por PropostaCandidaturaRepo
     */
    @Autowired
    private PropostaCandidaturaJPAFactory propostaCandidaturaJPAFactory;
    /**
     * Objeto do tipo PropostaCandidaturaIDFactory a ser utilizador por PropostaCandidaturaRepo
     */
    @Autowired
    private PropostaCandidaturaIDFactory factory;


    /**
     * Verificar se o aluno esta inscrito nesta proposta
     * @param idProposta o id da proposta
     * @param idAluno o id do aluno
     * @return se esta inscrito ou erro
     */
    public boolean isIncrito(Long idProposta, Long idAluno)
    {
        PropostaCandidaturaID id = factory.create(idProposta, idAluno);

        Optional<PropostaCandidaturaJPA> candidaturaJPA = jpaRepository.findById(id);

        if (candidaturaJPA.isEmpty())
        {
            return false;
        }
        return true;
    }

    /**
     * Verifica se o aluno esta inscrito em qualquer candidatura ativa
     * @param idAluno o id do aluno
     * @return se esta inscrito
     */
    public boolean isAlunoInscrito(Long idAluno)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidaturaJPA> candidaturasAluno =
                lista.stream().filter(obj ->
                        obj.getId().getIdaluno().equals(idAluno)).toList();

        long contagemValido =
                candidaturasAluno.stream().filter(obj->
                        obj.getEstado()==EstadoCandidatura.PENDENTE).count();

        return contagemValido > 0;
    }

    /**
     * Devolve PropostaCandidatura persistida
     * @param propostaID id de proposta
     * @param alunoId id de aluno
     * @return PropostaCandidatura
     */
    public PropostaCandidatura createAndSave(Long propostaID, Long alunoId)
    {
        PropostaCandidaturaID id = factory.create(propostaID, alunoId);

        if (jpaRepository.existsById(id))
        {
            throw new OptionalVazioException("O aluno ja se tinha candidatado a esta proposta.");
        }

        PropostaCandidaturaJPA jpa = propostaCandidaturaJPAFactory.create(id, EstadoCandidatura.PENDENTE);

        return mapper.toModel(jpaRepository.save(jpa));
    }

    /**
     * Devolve PropostaCandidatura atualizada e persistida
     * @param propostaCandidatura PropostaCandidatura atualizada a guardar
     * @return PropostaCandidatura atualizada
     */
    public PropostaCandidatura updateAndSave(PropostaCandidatura propostaCandidatura)
    {
        PropostaCandidaturaID id = factory.create(propostaCandidatura.getIdProposta(), propostaCandidatura.getIdAluno());

        //jpaRepository.deleteById(id);

        PropostaCandidaturaJPA jpa = mapper.toJPA(propostaCandidatura);

        return mapper.toModel(jpaRepository.save(jpa));
    }
    /**
     * Devolve PropostaCandidatura filtrada pelo ID ou Optional.empty()
     * @param propostaCandidaturaID id de PropostaCandidatura
     * @return PropostaCandidatura ou Optional.empty()
     */
    public Optional<PropostaCandidatura> findByID(PropostaCandidaturaID propostaCandidaturaID)
    {
        Optional<PropostaCandidaturaJPA> propostaCandidaturaJPA = jpaRepository.findById(propostaCandidaturaID);

        if (propostaCandidaturaJPA.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toModel(propostaCandidaturaJPA.get()));
    }

    /**
     * Método que invalida todas as candidaturas exceto a do aluno com id enviado por parametro.
     * @param propostaID id de proposta
     * @param alunoID id de aluno
     */
    public void invalidarCandidaturas(Long propostaID, Long alunoID)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidatura> candidaturasAluno =
                lista.stream()
                        .filter(obj -> !obj.getId().getIdaluno().equals(alunoID))
                        .filter(obj -> obj.getId().getidproposta().equals(propostaID))
                        .filter(obj -> obj.getEstado() == EstadoCandidatura.PENDENTE)
                        .map(mapper::toModel).toList();

        candidaturasAluno.forEach(obj -> obj.setEstado(EstadoCandidatura.REJEITADO));

        candidaturasAluno.forEach(obj -> jpaRepository.save(mapper.toJPA(obj)));
    }

    /**
     * Método que invalida todas as candidaturas.
     * @param propostaID id de proposta
     */
    public void invalidarTodasCandidaturas(Long propostaID)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidatura> candidaturasAluno =
                lista.stream()
                        .filter(obj -> obj.getId().getidproposta().equals(propostaID))
                        .filter(obj -> obj.getEstado() == EstadoCandidatura.PENDENTE)
                        .map(mapper::toModel).toList();

        candidaturasAluno.forEach(obj -> obj.setEstado(EstadoCandidatura.REJEITADO));

        candidaturasAluno.forEach(obj -> jpaRepository.save(mapper.toJPA(obj)));
    }

    /**
     * Devolve PropostaCandidatura filtrada pelo estado e id de aluno ou Optional.empty()
     * @param id id de aluno
     * @param estado estado
     * @return PropostaCandidatura ou Optional.empty()
     */
    public Optional<PropostaCandidatura> findPropostaAtivaByAlunoId(Long id, EstadoCandidatura estado)
    {
        Optional <PropostaCandidaturaJPA> propostaCandidaturaJPA = jpaRepository.findByIdIdalunoAndEstado(id, estado);

        if (propostaCandidaturaJPA.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toModel(propostaCandidaturaJPA.get()));
    }

    /**
     * Devolve Lista de PropostaCandidatura
     * @return Lista de PropostaCandidatura
     */
    public List<PropostaCandidatura> findAll()
    {
        return jpaRepository.findAll().stream().map(mapper::toModel).toList();
    }
}