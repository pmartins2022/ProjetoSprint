package com.grupo2.proposta.repository;

import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.jpa.PropostaInscricaoJPA;
import com.grupo2.proposta.jpa.mapper.PropostaCandidaturaJPAMapper;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidatura;
import com.grupo2.proposta.model.PropostaInscricaoID;
import com.grupo2.proposta.model.factory.PropostaCandidaturaIDFactory;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropostaInscricaoRepo
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
        PropostaInscricaoID id = new PropostaInscricaoID(idProposta,idAluno);

        Optional<PropostaInscricaoJPA> candidaturaJPA = jpaRepository.findById(id);

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
    public boolean isAlunoInscrito(Long idAluno)
    {
        List<PropostaInscricaoJPA> lista = jpaRepository.findAll();

        List<PropostaInscricaoJPA> candidaturasAluno =
                lista.stream().filter(obj->
                        obj.getId().getIdAluno().equals(idAluno)).toList();

        long contagemValido =
                candidaturasAluno.stream().filter(obj->
                        obj.getEstado()==EstadoCandidatura.PENDENTE).count();

        return contagemValido > 0;
    }

    public PropostaCandidatura createAndSave(Long propostaID, Long alunoId)
    {
        PropostaInscricaoID id = factory.create(propostaID, alunoId);
        PropostaInscricaoJPA jpa = new PropostaInscricaoJPA(id,EstadoCandidatura.PENDENTE);

        return mapper.toModel(jpaRepository.save(jpa));
    }
}