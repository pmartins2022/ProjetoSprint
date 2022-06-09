package com.grupo2.proposta.repository;

import com.grupo2.proposta.exception.IdInvalidoException;
import com.grupo2.proposta.jpa.PropostaCandidaturaJPA;
import com.grupo2.proposta.model.EstadoCandidatura;
import com.grupo2.proposta.model.PropostaCandidaturaID;
import com.grupo2.proposta.repository.jpa.PropostaCandidaturaJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sound.midi.InvalidMidiDataException;
import java.util.Optional;

@Repository
public class PropostaCandidaturaRepo
{
    @Autowired
    private PropostaCandidaturaJPARepository jpaRepository;

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
            throw new IdInvalidoException("O aluno ja fez esta canditarura e tinha sido "+candidaturaJPA.get().getEstado());
        }

        return true;
    }
}
