package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.EdicaoUCDTO;
import com.grupo2.projeto.dto.AvaliacaoNotaDTO;
import com.grupo2.projeto.dto.PropostaDTO;
import com.grupo2.projeto.dto.mapper.AvaliacaoNotaMapper;
import com.grupo2.projeto.exception.*;
import com.grupo2.projeto.model.Avaliacao;
import com.grupo2.projeto.model.AvaliacaoNota;
import com.grupo2.projeto.model.EstadoAvaliacao;
import com.grupo2.projeto.model.Projeto;
import com.grupo2.projeto.repository.*;
import com.grupo2.projeto.security.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AvaliacaoNotaService
{
    @Autowired
    private AvaliacaoNotaMapper mapper;

    @Autowired
    private EdicaoUCJDBCRepository edicaoUCJDBCRepository;

    @Autowired
    private ProjetoJDBCRepository projetoJDBCRepository;

    @Autowired
    private PropostaJDBCRepository propostaJDBCRepository;

    @Autowired
    private AvaliacaoJDBCRepository avaliacaoJDBCRepository;
    @Autowired
    private AvaliacaoNotaJDBCRepository avaliacaoNotaJDBCRepository;


    public void createAvaliacaoNota(AvaliacaoNotaDTO dto) throws IllegalAccessException
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
            nota = avaliacaoNotaJDBCRepository.findByIdAvaliacao(avaliacao.getId());
        } catch (Exception ignored)
        {
        }

        if (nota != null)
        {
            throw new ErroGeralException("Esta avaliacao ja contem uma nota.");
        }

        AvaliacaoNota mom = mapper.toModel(dto);

        avaliacaoNotaJDBCRepository.insert(mom);
    }
    public void editarAvaliacaoNota(Long idMomentoAvaliacao,Long nota, String justificacao)
    {
        AvaliacaoNota avaliacaoNota = null;

        try
        {
            avaliacaoNota = avaliacaoNotaJDBCRepository.findById(idMomentoAvaliacao);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe Momento de Avaliação");
        }
        avaliacaoNota.setNota(nota);
        avaliacaoNota.setJustificacao(justificacao);
        try
        {
            avaliacaoNotaJDBCRepository.update(avaliacaoNota);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void reviewAvaliacaoNota(Long idAvaliacaoNota, String avaliacao)
    {
        //VER SE AVALIACAONOTA EXISTE
        AvaliacaoNota avaliacaoNota = null;
        try
        {
            avaliacaoNota = avaliacaoNotaJDBCRepository.findById(idAvaliacaoNota);
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

        if (avaliacaoNota.getEstadoAvaliacao() != EstadoAvaliacao.PENDENTE)
        {
            throw new ValidacaoInvalidaException("Para concluir uma AvaliacaoNota esta tem que estar em PENDENTE." +
                    "Estado atual: " + avaliacaoNota.getEstadoAvaliacao());
        }

        EstadoAvaliacao value;

        try
        {
            value = EstadoAvaliacao.valueOf(avaliacao);
        } catch (IllegalArgumentException e)
        {
            throw new ErroGeralException("Nao foi possivel criar enum a partir do valor: "+avaliacao);
        }

        avaliacaoNota.updateEstado(value);

        try
        {
            avaliacaoNotaJDBCRepository.insert(avaliacaoNota);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        if (value == EstadoAvaliacao.CONCLUIDO)
        {
            avaliacaoModel.setEstadoAvaliacao(EstadoAvaliacao.CONCLUIDO);
            try
            {
                avaliacaoJDBCRepository.update(avaliacaoModel);
            } catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public List<AvaliacaoNotaDTO> findAllByEstadoAndRucID(Long rucID, String estado)
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
        List<PropostaDTO> listProposta = new ArrayList<>();
        try
        {
            listProposta = propostaJDBCRepository.findAllByEdicaoUCID(edicaoUCActive.getId());
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não há propostas nessa Edição: "+ edicaoUCActive.getId());
        }

        //LISTA DE PROJETOS DESSA EDICAO
        List<Projeto> listProjeto = new ArrayList<>();
        try
        {
            for (PropostaDTO p :
                    listProposta)
            {
                listProjeto.add(projetoJDBCRepository.findByPropostaId(p.getId()));
            }
        } catch (Exception e)
        {
            throw new OptionalVazioException("Ocorreu um erro ao tentar encontar projeto ligado a um ID de proposta");
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
        List<AvaliacaoNota> avaliacaoNotaRawList = new ArrayList<>();
        try
        {
            for (Avaliacao avaliacao :
                    avaliacaoList)
            {
                avaliacaoNotaRawList.add(avaliacaoNotaJDBCRepository.findByIdAvaliacao(avaliacao.getId()));
            }
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        EstadoAvaliacao value;
        try
        {
            value = EstadoAvaliacao.valueOf(estado);
        } catch (IllegalArgumentException e)
        {
            throw e;
        }

        //TODAS COM ESTADO
        List<AvaliacaoNota> avaliacaoNotasList = null;
        try
        {
            avaliacaoNotasList = avaliacaoNotaJDBCRepository.findAllByEstado(value.name());
        } catch (Exception e)
        {
            throw new ListaVaziaException("Não existem AvaliacaoNotas nesse estado");
        }

        //FILTRAGEM DE LISTA RAWAVALIACOESNOTA ENCONTRADA COM TODAS AVALIACOESNOTALISTA
        //todas as notas daquele esta E que pertencam à Edição
        avaliacaoNotasList.removeIf(nota -> !(avaliacaoNotaRawList.contains(nota)));

        return avaliacaoNotasList.stream().map(mapper::toDTO).toList();
    }

    public AvaliacaoNotaDTO findAvaliacaoNotaByAvaliacaoID(Long idAvaliacao) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        AvaliacaoNota avaliacaoNota = null;

        try
        {
            avaliacaoNota = avaliacaoNotaJDBCRepository.findByIdAvaliacao(idAvaliacao);
        } catch (Exception e)
        {
            throw new OptionalVazioException("Não existe AvaliacaoNota com esse ID de avaliacao: " + idAvaliacao);
        }

        if (avaliacaoNota.getEstadoAvaliacao() != EstadoAvaliacao.REVISAO)
        {
            throw new ErroGeralException("Nao pode ser avaliado");
        }

        if (!Objects.equals(idAvaliacao, avaliacaoNota.getIdAvaliacao()))
        {
            throw new ErroGeralException("O id não corresponde a uma avaliacao");
        }

        AvaliacaoNota avaliacaoNota1 = avaliacaoNotaJDBCRepository.findByIdAvaliacao(idAvaliacao);

        return mapper.toDTO(avaliacaoNota1);
    }
}
