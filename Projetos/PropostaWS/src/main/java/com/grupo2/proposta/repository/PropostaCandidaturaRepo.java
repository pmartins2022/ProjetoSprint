package com.grupo2.proposta.repository;

import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.model.factory.PropostaCandidaturaIDFactory;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sound.midi.InvalidMidiDataException;
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
        PropostaCandidaturaID id = new PropostaCandidaturaID(idProposta,idAluno);

        Optional<PropostaCandidaturaJPA> candidaturaJPA = jpaRepository.findById(id);

        if (candidaturaJPA.isEmpty())
        {
            return false;
        }

        if (candidaturaJPA.get().getEstado() != EstadoCandidatura.PENDENTE)
        {
            throw new IdInvalidoException("O aluno ja fez esta candidatura e tinha sido "+candidaturaJPA.get().getEstado());
        }

        return true;
    }

    /**
     * Verifica se o aluno esta inscrito em qualquer candidatura ativa
     * @param idAluno o id do aluno
     * @return se esta inscrito
     */
    public boolean isCandidaturaRegistered(Long idAluno)
    {
        List<PropostaCandidaturaJPA> lista = jpaRepository.findAll();

        List<PropostaCandidaturaJPA> candidaturasAluno =
                lista.stream().filter(obj->
                        obj.getId().getIdAluno().equals(idAluno)).toList();

        long contagemValido =
                candidaturasAluno.stream().filter(obj->
                        obj.getEstado()==EstadoCandidatura.PENDENTE).count();

        return contagemValido > 0;
    }

    public PropostaCandidatura createAndSave(Long propostaID, Long alunoId)
    {
        PropostaCandidaturaID id = factory.create(propostaID, alunoId);
        PropostaCandidaturaJPA jpa = new PropostaCandidaturaJPA(id,EstadoCandidatura.PENDENTE);

        return mapper.toModel(jpaRepository.save(jpa));
    }
}