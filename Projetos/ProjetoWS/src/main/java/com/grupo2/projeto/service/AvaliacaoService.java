package com.grupo2.projeto.service;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.MomentoAvaliacaoNotaDTO;
import com.grupo2.projeto.dto.UtilizadorDTO;
import com.grupo2.projeto.dto.mapper.AvaliacaoDTOMapper;
import com.grupo2.projeto.exception.OptionalVazioException;
import com.grupo2.projeto.exception.ValidacaoInvalidaException;
import com.grupo2.projeto.model.*;
import com.grupo2.projeto.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService
{
//ava

    @Autowired
    private AvaliacaoDTOMapper mapper;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

//    public AvaliacaoDTO createAndSave(AvaliacaoDTO avaliacaoDTO)
//    {
//        Optional<Conteudo> cont = contRepo.findById(avaliacaoDTO.getConteudo());
//
//        if (cont.isEmpty())
//        {
//            throw new OptionalVazioException("Nao existe conteudo com esse id");
//        }
//
//        if (!cont.get().getIdProjeto().equals(avaliacaoDTO.getIdProjeto()))
//        {
//            throw new ValidacaoInvalidaException("O ID DO PROJETO DA AVALIACAO NAO E IGUAL AO ID DO PROJETO DO CONTEUDO");
//        }
//
//        if (cont.get().getEstadoConteudo() != EstadoConteudo.APROVADO)
//        {
//            throw new ValidacaoInvalidaException("O ESTADO DO CONTEUDO NAO ESTA APROVADO");
//        }
//        if (avaliacaoDTO.getPresidenteId().equals(avaliacaoDTO.getArguenteId()) ||
//                avaliacaoDTO.getOrientadorId().equals(avaliacaoDTO.getArguenteId()) ||
//                avaliacaoDTO.getOrientadorId().equals(avaliacaoDTO.getPresidenteId())) {
//            throw new ValidacaoInvalidaException("Não pode existir o mesmo ID para diferentes Jurados");
//        }
//        Optional<UtilizadorDTO> opPresidente =utilizadorRestRepository.findById(avaliacaoDTO.getPresidenteId());
//        Optional<UtilizadorDTO> opOrientador =utilizadorRestRepository.findById(avaliacaoDTO.getOrientadorId());
//        Optional<UtilizadorDTO> opArguente =utilizadorRestRepository.findById(avaliacaoDTO.getArguenteId());
//        if (opPresidente.isEmpty())
//        {
//            throw new ValidacaoInvalidaException("O ID DO PRESIDENTE DO JÚRI NÃO EXISTE");
//        }
//        if (opOrientador.isEmpty())
//        {
//            throw new ValidacaoInvalidaException("O ID DO ORIENTADOR DO JÚRI NÃO EXISTE");
//        }
//        if (opArguente.isEmpty())
//        {
//            throw new ValidacaoInvalidaException("O ID DO ARGUENTE DO JÚRI NÃO EXISTE");
//        }
//        if (opPresidente.get().getTipoUtilizador()!=TipoUtilizador.DOCENTE)
//        {
//            throw new ValidacaoInvalidaException(opPresidente.get().getNome()+" "+opPresidente.get().getSobrenome()+" COM O ID " +opPresidente.get().getId()+" NÃO É UM DOCENTE");
//        }
//        if (opOrientador.get().getTipoUtilizador()!=TipoUtilizador.DOCENTE)
//        {
//            throw new ValidacaoInvalidaException(opOrientador.get().getNome()+" "+opOrientador.get().getSobrenome()+" COM O ID "+opOrientador.get().getId()+" NÃO É UM DOCENTE");
//        }
//        if (opArguente.get().getTipoUtilizador()!=TipoUtilizador.DOCENTE){
//            throw new ValidacaoInvalidaException(opArguente.get().getNome()+" "+opArguente.get().getSobrenome()+" COM O ID "+opArguente.get().getId()+" NÃO É UM DOCENTE");
//        }
//        Optional<Projeto> opProjeto = projetoRepository.findById(avaliacaoDTO.getIdProjeto());
//
//        if (opProjeto.isEmpty()){
//            throw new ValidacaoInvalidaException("O PROJETO NÃO EXISTE");
//        }
//        if (opProjeto.get().getOrientadorId()!=opOrientador.get().getId())
//        {
//            throw new ValidacaoInvalidaException("O Docente não é Orientador do Projeto");
//        }
//
//        Avaliacao avaliacao = mapper.toModel(avaliacaoDTO);
//
//        Avaliacao savedAvaliacao = repository.saveAvaliacao(avaliacao, cont.get());
//
//        return mapper.toDTO(savedAvaliacao);
//    }
//
//    public Optional<AvaliacaoDTO> findById(Long id)
//    {
//        Optional<Avaliacao> optionalAvaliacao = repository.findById(id);
//
//        if (optionalAvaliacao.isPresent())
//        {
//            AvaliacaoDTO avaliacaoDTO = mapper.toDTO(optionalAvaliacao.get());
//            return Optional.of(avaliacaoDTO);
//        } else
//        {
//            return Optional.empty();
//        }
//    }
//    public List<AvaliacaoDTO> findAll()
//    {
//        List<Avaliacao> lista = repository.findAll();
//
//        List<AvaliacaoDTO> listaDTO = lista.stream().map(mapper::toDTO).toList();
//
//        return listaDTO;
//    }
//
//    public List<AvaliacaoDTO> findAllByPresidente(Long presidenteId)
//    {
//        List<Avaliacao> lista = repository.findAllByPresidente(presidenteId);
//
//        List<AvaliacaoDTO> listaDTO = lista.stream().map(mapper::toDTO).toList();
//
//        return listaDTO;
//    }
//
//    public AvaliacaoDTO reviewAvaliacao(Long idAvaliacao, String avaliacao)
//    {
//        Optional<AvaliacaoDTO> avaliacaoDTO = repository.findById(avaliacao.getId());
//
//        if(avaliacaoDTO.isEmpty())
//        {
//            throw new OptionalVazioException("Não existe Avaliacao com este ID: " + avaliacao.getId());
//        }
//
//        Optional<ProjetoDTO> projeto = projetoRepository.findById(avaliacao.getProjetoId());
//
//        if(projeto.isEmpty())
//        {
//            throw new OptionalVazioException("Não existe Avaliacao com este ID: " + avaliacao.getId());
//        }
//
//        Optional<EdicaoUC> edicaoUC = GenericJDBCRepository.from(dto).findById(projeto.getPropostaId());
//
//        if(edicaoUC.isEmpty())
//        {
//            throw new OptionalVazioException("Não existe Avaliacao com este ID: " + avaliacao.getId());
//        }
//
//        if(edicaoUC.getRucID() != LoginContext.getCurrent().getId())
//        {
//            throw new ValidacaoInvalidaException("Não existe Avaliacao com este ID: " + avaliacao.getId());
//        }
//
//        if(!(avaliacaoDTO.getEstado().equals(AvaliacaoEstado.PENDENTE)) && !(avaliacao.getNota() != -1))
//        {
//            throw new ValidacaoInvalidaException("Avaliação tem que estar em estado PENDENTE.
//                    Encontra-se em estado " + avaliacaoDTO.getEstado());
//        }
//
//        if(avaliacao.getNota() != -1)
//        {
//            throw new ValidacaoInvalidaException("Presidente tem que atribuir nota à Avaliação.");
//        }
//        //tem que estar PENDENTE ou REVISAO
//        //e tenho que fazer conversao para ENUM
//        //faço SET
//        //guardo
//        //devolvo
//    }
}
