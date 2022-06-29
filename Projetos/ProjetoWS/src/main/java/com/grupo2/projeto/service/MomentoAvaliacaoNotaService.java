package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.mapper.MomentoAvaliacaoNotaMapper;
import com.grupo2.projeto.exception.ListaVaziaException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.MomentoAvaliacaoNota;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.*;
import com.grupo2.projeto.repository.jdbc.GenericJDBCRepository;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MomentoAvaliacaoNotaService
{
    @Autowired
    private MomentoAvaliacaoNotaMapper mapper;

    @Autowired
    private EdicaoUCJDBCRepository edicaoUCJDBCRepository;

    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;

    @Autowired
    private PropostaJDBCRepository propostaJDBCRepository;

    @Autowired
    private AvaliacaoJDBCRepository avaliacaoJDBCRepository;
    @Autowired
    private MomentoAvaliacaoNotaJDBCRepository momentoAvaliacaoNotaJDBCRepository;


    public int createAvaliacaoNota(MomentoAvaliacaoNotaDTO dto) throws IllegalAccessException
    {
        //idAvaliacao
        //Presidente está em várias avaliacoes

        //pelo idAvalicao findByID o Presinete  e ver se é idAvaliacao.idPresidente == LoginContext.presidente
        //ver se NAO está em REVISAO OU CONCLUIDA
        //Se ESTIVER em REVISAO a nota tem que ser NULL

        //MomentoAvaliacaoNota mom = mapper.toModel(dto);
        //return repository.createAvaliacaoNota(mom);
        return 0;
    }

    public void reviewAvaliacao(Long idAvaliacaoNota, String avaliacao)
    {
        //VER SE AVALIACAONOTA EXISTE
        MomentoAvaliacaoNota avaliacaoNota = null;
        try
        {
            avaliacaoNota = momentoAvaliacaoNotaJDBCRepository.findById(idAvaliacaoNota);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe MomentoAvaliacaoNota com este ID de Avaliação: " + avaliacaoNota.getId());
        }

        //PRECISO DO OBJETO DE AVALIACAO
        Avaliacao avaliacaoModel = null;
        try
        {
            avaliacaoModel = avaliacaoJDBCRepository.findById(avaliacaoNota.getIdAvaliacao());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Avaliacao com este ID: " + avaliacaoModel.getId());
        }

        //VERIFICAR - SE RUC QUE FEZ PEDIDO É IGUAL AO RUC DA EDICAO ONDE A AVALIACAO(PROPOSTA) ESTÁ INSERIDA
        //ENCONTRAR EDICAO DO LOGIN
        //ENCONTRAR EDICAO APARTIR DO IDAVALICAONOTA
        //VER SE IDS EQUALS

        //PROJETO PARA ARRANJAR ID PROPOSTA
        Projeto projeto = null;
        try
        {
            projeto = projetoJDBCRepository.findById(avaliacaoModel.getIdProjeto());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Projeto com este ID: " + avaliacaoModel.getIdProjeto());
        }

        //PROPOSTA PARA ARRANJAR ID DE EDICAO
        PropostaDTO proposta = null;
        try
        {
            proposta = propostaJDBCRepository.findById(projeto.getPropostaId());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Proposta com este ID: " + projeto.getPropostaId());
        }

        //EDICAO ENCONTRADA APARTIR DO IDAVALIACAONOTA
        EdicaoUCDTO edicaoUC = null;
        try
        {
            edicaoUC = edicaoUCJDBCRepository.findById(proposta.getEdicaoUCId());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não EdiçãoUC com este ID: " + proposta.getEdicaoUCId());
        }

        //EDICAO ENCONTRADA APARTIR DO LOGIN CONTEXT
        EdicaoUCDTO edicaoUCActive = null;
        try
        {
            edicaoUCActive = edicaoUCJDBCRepository.findById(LoginContext.getCurrent().getId());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não EdiçãoUC com este ID: " + proposta.getEdicaoUCId());
        }

        if(!Objects.equals(edicaoUC.getId(), edicaoUCActive.getId()))
        {
            throw new ValidacaoInvalidaException("RUC não chefia essa Edição");
        }

        if(!(avaliacaoNota.getEstadoAvaliacao().equals(EstadoAvaliacao.PENDENTE)))
        {
            throw new ValidacaoInvalidaException("Avaliação tem que estar em estado PENDENTE."+
                  "Encontra-se em estado " + avaliacaoNota.getEstadoAvaliacao());
        }

        EstadoAvaliacao value;

        try
        {
            value = EstadoAvaliacao.valueOf(avaliacao);
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }


        avaliacaoNota.updateEstado(value);

        try
        {
            momentoAvaliacaoNotaJDBCRepository.insert(avaliacaoNota);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<MomentoAvaliacaoNotaDTO> findAllByEstadoAndRucID(Long rucID, String estado)
    {
        //VER SE RUC TEM UMA EDICAO ATVA
        Optional<EdicaoUC> edicaoUCActive = edicaoUCJDBCRepository.findByRucIDAndEstadoEdicaoUC(rucID);

        if(edicaoUC.isEmpty())
        {
            throw new OptionalVazioException("RUC não tem nenhuma Edição Ativa");
        }

        //LISTA DE PROPOSTAS DESSA EDICAO
        List<PropostaDTO> listProposta = propostaRepository.findAllByEdicaoUCID(edicaoUCActive.getId());

        if(listProposta.isEmpty())
        {
            throw new OptionalVazioException("Não há propostas nessa Edição: "+ edicaoUCActive.getId());
        }

        //LISTA DE AVALIACOES DE CADA UMA DAS PROPOSTAS
        List<AvaliacaoDTO> avaliacaoList = avaliacaoRepository.findAllByPropostaId();

        List<MomentoAvaliacaoNotaDTO> avaliacaoNotaList = new ArrayList<>();

        //PARA CADA AVALIACAO PEGAR NA SUA RESPETIVA MOMENTOAVALIACAONOTA
        for (int i = 0; i < listProposta.size(); i++)
        {
            avaliacaoNotaList.add(avaliacaoRepository.findAllByPropostaId(listProposta.get(i).getId()));
        }

        if (avaliacaoNotaList.isEmpty())
        {
            throw new ListaVaziaException("Não há momentos de avaliação de nota.");
        }

        EstadoAvaliacao value;
        try
        {
            value = EstadoAvaliacao.valueOf(estado);
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }

        return avaliacaoNotaList.stream().map(mapper::toDTO).toList();
    }
}
