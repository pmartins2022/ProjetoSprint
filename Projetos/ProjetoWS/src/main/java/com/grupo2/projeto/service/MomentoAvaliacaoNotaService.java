package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.mapper.MomentoAvaliacaoNotaMapper;
import com.grupo2.projeto.exception.ErroGeralException;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.AvaliacaoNota;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.*;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    public void createAvaliacaoNota(MomentoAvaliacaoNotaDTO dto) throws IllegalAccessException
    {

        //Validar Avalçiação
        Avaliacao avaliacao = null;
        try
        {
            avaliacao = avaliacaoJDBCRepository.findById(dto.getIdAvaliacao());
        } catch (Exception e)
        {
            throw new IdInvalidoException("Não este ID não pertence a um presidente: " + dto.getIdAvaliacao());
        }

        if (!(avaliacao.getPresidenteId().equals(LoginContext.getCurrent().getId())))
        {
            throw new ValidacaoInvalidaException("Nao es presidente desta avaliacao");
        }


        AvaliacaoNota nota = null;

        try
        {
            nota = momentoAvaliacaoNotaJDBCRepository.findByIdAvaliacao(avaliacao.getId());
        } catch (Exception ignored)
        {
        }

        if (nota != null)
        {
            throw new ErroGeralException("Esta avaliacao ja contem uma nota.");
        }

        AvaliacaoNota mom = mapper.toModel(dto);

         momentoAvaliacaoNotaJDBCRepository.insert(mom);
    }
    public void editarAvaliacaoNota(Long idMomentoAvaliacao,int nota, String justificacao)
    {
        MomentoAvaliacaoNota momentoAvaliacaoNota =null;

        try{
            momentoAvaliacaoNota = momentoAvaliacaoNotaJDBCRepository.findById(idMomentoAvaliacao);
        }catch(Exception e)
        {
            throw new OptionalVazioException("Não existe Momento de Avaliação");
        }
        momentoAvaliacaoNota.setNota(nota);
        momentoAvaliacaoNota.setJustificacao(justificacao);
        try
        {
            momentoAvaliacaoNotaJDBCRepository.update(momentoAvaliacaoNota);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }

    public void reviewAvaliacao(Long idAvaliacaoNota, String avaliacao)
    {
        //VER SE AVALIACAONOTA EXISTE
        AvaliacaoNota avaliacaoNota = null;
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
            throw new OptionalVazioException("Não existe Avaliacao com este ID: " + avaliacaoNota.getIdAvaliacao());
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
        } catch (IllegalArgumentException e)
        {
            throw new ErroGeralException("Nao foi possivel criar enum a partir do valor: "+avaliacao);
        }

        if (value != EstadoAvaliacao.PENDENTE)
        {
         throw new ValidacaoInvalidaException("Para concluir uma AvaliacaoNota esta tem que estar em PENDENTE." +
                 "Estado atual: "+ value.name());
        }

        avaliacaoNota.updateEstado(value);

        try
        {
            momentoAvaliacaoNotaJDBCRepository.insert(avaliacaoNota);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        if (value == EstadoAvaliacao.CONCLUIDO)
        {
            avaliacaoModel.setEstadoAvaliacao(EstadoAvaliacao.CONCLUIDO);

            avaliacaoJDBCRepository.update(avaliacaoModel);
        }
    }

    public List<MomentoAvaliacaoNotaDTO> findAllByEstadoAndRucID(Long rucID, String estado)
    {
        //VER SE RUC TEM UMA EDICAO ATVA
        EdicaoUCDTO edicaoUCActive = null;
        try
        {
            edicaoUCActive = edicaoUCJDBCRepository.findByRucIDAndEdicaoUCActive(rucID);
        } catch (Exception e)
        {
            throw new OptionalVazioException("RUC não tem nenhuma ediçãoUC ativa.");
        }

        //LISTA DE PROPOSTAS DESSA EDICAO
        List<PropostaDTO> listProposta = null;
        try
        {
            listProposta = propostaJDBCRepository.findAllByEdicaoUCID(edicaoUCActive.getId());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não há propostas nessa Edição: "+ edicaoUCActive.getId());
        }

        //LISTA DE PROJETOS DESSA EDICAO
        List<Projeto> listProjeto = null;
        try
        {
            for (PropostaDTO p :
                    listProposta)
            {
                listProjeto.add(projetoJDBCRepository.findByPropostaId(p.getId()));
            }
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não há propostas nessa Edição: "+ edicaoUCActive.getId());
        }

        //LISTA DE AVALIACOES DE CADA UMA DAS PROPOSTAS
        List<Avaliacao> avaliacaoList =new ArrayList<>();

        try
        {
            for (Projeto p:
                 listProjeto)
            {
                avaliacaoList.add(avaliacaoJDBCRepository.findAllByProjetoId(p.getId()));
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        //PARA CADA AVALIACAO PEGAR NA SUA RESPETIVA MOMENTOAVALIACAONOTA
        List<AvaliacaoNota> avaliacaoNotaList = new ArrayList<>();

        try
        {
            for (Avaliacao avaliacao :
                    avaliacaoList)
            {
                avaliacaoNotaList.add(momentoAvaliacaoNotaJDBCRepository.findByIdAvaliacao(avaliacao.getId()));
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
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
