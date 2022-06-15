package com.grupo2.proposta.repository;

import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.exception.OptionalVazioException;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.model.factory.PropostaCandidaturaIDFactory;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropostaCandidaturaRepo
{
    @Autowired
    private PropostaCandidaturaJPARepository jpaRepository;

    @Autowired
    private PropostaCandidaturaJPAMapper mapper;

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
        PropostaCandidaturaID id = new PropostaCandidaturaID(idProposta, idAluno);

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
                        obj.getId().getIdAluno().equals(idAluno)).toList();

        long contagemValido =
                candidaturasAluno.stream().filter(obj->
                        obj.getEstado()==EstadoCandidatura.PENDENTE).count();

        return contagemValido > 0;
    }

    public PropostaCandidatura createAndSave(Long propostaID, Long alunoId)
    {
        PropostaCandidaturaID id = factory.create(propostaID, alunoId);
        PropostaCandidaturaJPA jpa = new PropostaCandidaturaJPA(id, EstadoCandidatura.PENDENTE);

        return mapper.toModel(jpaRepository.save(jpa));
    }

    public PropostaCandidatura updateAndSave(PropostaCandidatura propostaCandidatura)
    {
        PropostaCandidaturaID id = factory.create(propostaCandidatura.getIdProposta(), propostaCandidatura.getIdAluno());

        jpaRepository.deleteById(id);

        PropostaCandidaturaJPA jpa = mapper.toJPA(propostaCandidatura);

        return mapper.toModel(jpaRepository.save(jpa));
    }

    public Optional<PropostaCandidatura> findByID(PropostaCandidaturaID propostaCandidaturaID)
    {
        Optional<PropostaCandidaturaJPA> propostaCandidaturaJPA = jpaRepository.findById(propostaCandidaturaID);

        if (propostaCandidaturaJPA.isEmpty())
        {
            Optional.empty();
        }

        return Optional.of(mapper.toModel(propostaCandidaturaJPA.get()));
    }

    public void invalidarCandidaturas(Long propostaID, Long alunoID)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidatura> candidaturasAluno =
                lista.stream()
                        .filter(obj -> !obj.getId().getIdAluno().equals(alunoID))
                        .filter(obj -> obj.getId().getIdProposta().equals(propostaID))
                        .filter(obj -> obj.getEstado() == EstadoCandidatura.PENDENTE)
                        .map(mapper::toModel).toList();

        candidaturasAluno.forEach(obj -> obj.setEstado(EstadoCandidatura.REJEITADO));

        candidaturasAluno.forEach(obj -> jpaRepository.save(mapper.toJPA(obj)));
    }

    public void invalidarTodasCandidaturas(Long propostaID)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidatura> candidaturasAluno =
                lista.stream()
                        .filter(obj -> obj.getId().getIdProposta().equals(propostaID))
                        .filter(obj -> obj.getEstado() == EstadoCandidatura.PENDENTE)
                        .map(mapper::toModel).toList();

        candidaturasAluno.forEach(obj -> obj.setEstado(EstadoCandidatura.REJEITADO));

        candidaturasAluno.forEach(obj -> jpaRepository.save(mapper.toJPA(obj)));
    }
}