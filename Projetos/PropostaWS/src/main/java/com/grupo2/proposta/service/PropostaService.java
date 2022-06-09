package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.dto.factory.ProjetoDTOFactory;
import com.grupo2.proposta.dto.mapper.ConviteDTOMapper;
import com.grupo2.proposta.exception.*;
import com.grupo2.proposta.model.Convite;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.model.TipoUtilizador;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.model.Proposta;
import com.grupo2.proposta.repository.ConviteRepository;
import com.grupo2.proposta.repository.PropostaCandidaturaRepo;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de servico de Proposta.
 */
@Service
public class PropostaService
{
    @Autowired
    private PropostaRepository repository;
    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;
    @Autowired
    private ProjetoRestRepository projetoRestRepository;
    @Autowired
    private PropostaCandidaturaRepo propostaCandidaturaRepo;


    @Autowired
    private PropostaDTOMapper mapper;

    @Autowired
    private ProjetoDTOFactory projetoDTOFactory;

    @Autowired
    private EdicaoUCRestRepository edicaoUCRestRepository;

    @Autowired
    private OrganizacaoRestRepository organizacaoRestRepository;

    @Autowired
    private ConviteRepository conviteRepository;

    @Autowired
    private ConviteDTOMapper conviteDTOMapper;

    /**
     * Criar uma nova proposta.
     * @param dto Proposta a ser criada
     * @return Proposta criada
     * @throws BaseDadosException Se ocorrerem erros relativos a base de dados
     */
    public PropostaDTO createProposta(PropostaDTO dto, String encoded) throws BaseDadosException
    {
        Proposta proposta = mapper.toModel(dto);

        Optional<UtilizadorDTO> utilizadorId = utilizadorRestRepository.findById(proposta.getUtilizadorId());

        if (utilizadorId.isEmpty())
        {
            throw new BaseDadosException("Id de utilizador "+proposta.getUtilizadorId()+" nao existe.");
        }

        Optional<OrganizacaoDTO> organizacaoId = organizacaoRestRepository.findById(proposta.getOrganizacaoId(),encoded);

        if (organizacaoId.isEmpty())
        {
            throw new BaseDadosException("Id de organizacao "+proposta.getOrganizacaoId()+" nao existe.");
        }

        Optional<EdicaoUCDTO> edicaoId = edicaoUCRestRepository.findById(proposta.getEdicaoUCId());

        if (edicaoId.isEmpty())
        {
            throw new BaseDadosException("Id de edicao unidade curricular "+proposta.getEdicaoUCId()+" nao existe.");
        }

        Proposta prop = repository.createProposta(proposta);

        return mapper.toDTO(prop);
    }

    /**
     * Rejeitar uma proposta.
     * @param id Id da proposta a ser rejeitada
     * @return Proposta rejeitada ou erro se a atualizacao for invalida
     */
    public Optional<PropostaDTO> rejeitarProposta(Long id)
    {
        Optional<Proposta> prop = repository.findById(id);

        if (prop.isEmpty())
        {
            return Optional.empty();
        }

        try
        {
            prop.get().reprovarProposta();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        Optional<PropostaDTO> propostaDTOSaved = atualizarProposta(prop.get());

        if (propostaDTOSaved.isEmpty())
        {
            return Optional.empty();
        }

        return propostaDTOSaved;
    }

    private Optional<PropostaDTO> atualizarProposta(Proposta proposta)
    {
        Optional<Proposta> propostaSaved = repository.atualizarProposta(proposta);

        if (propostaSaved.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(mapper.toDTO(propostaSaved.get()));
    }

    /**
     * Encontrar lista de propostas por NIF de organizacao
     * @param nif NIF da organizacao
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByNif(Integer nif, String encoded)
    {
        Optional<OrganizacaoDTO> dto = organizacaoRestRepository.findByNIF(nif, encoded);

        if (dto.isEmpty())
        {
            return List.of();
        }

        List<Proposta> lista = repository.findByNif(dto.get());

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Encontrar lista de propostas por titulo
     * @param titulo Titulo da proposta
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByTitulo(String titulo)
    {
        List<Proposta> lista = repository.findAllByTitulo(titulo);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Encontrar lista de propostas por id de utilizador
     * @param id Id do utilizador
     * @return Lista de propostas
     */
    public List<PropostaDTO> findByIdUtilizador(Long id)
    {
        List<Proposta> lista = repository.findByIdUtilizador(id);

        List<PropostaDTO> listaDTOS = lista.stream().map(mapper::toDTO).toList();

        return listaDTOS;
    }

    /**
     * Aprovar uma proposta. Necessita de validar o id de orientador e de aluno em repositorios externos.
     * @param propostaID Id da proposta a ser aprovada
     * @param orientadorID Id do orientador
     * @param alunoID Id do aluno
     * @return Proposta aprovada
     * @throws AtualizacaoInvalidaException Se a atualizacao for invalida
     * @throws IdInvalidoException Se o id for invalido
     */
    public ProjetoDTO acceptProposta(Long propostaID, Long orientadorID, Long alunoID) throws AtualizacaoInvalidaException, IdInvalidoException
    {
        Optional<Proposta> proposta = repository.findById(propostaID);
        if (proposta.isEmpty())
        {
            throw new IdInvalidoException("Id de proposta " + propostaID + " nao existe.");
        }

        try
        {
            proposta.get().aprovarProposta();
        } catch (AtualizacaoInvalidaException e)
        {
            throw new AtualizacaoInvalidaException(e.getMessage());
        }

        Optional<UtilizadorDTO> orientadorDTO = utilizadorRestRepository.findById(orientadorID);
        Optional<UtilizadorDTO> estudanteDTO = utilizadorRestRepository.findById(alunoID);

        if (orientadorDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Orientador " + orientadorID + " nao existe.");
        }
        else if ( orientadorDTO.get().getTipoUtilizador() != TipoUtilizador.ORIENTADOR)
        {
            throw new IdInvalidoException("O ID introduzido nao é um orientador.");
        }
        if (estudanteDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Estudante " + alunoID + " nao existe.");
        }
        else if (estudanteDTO.get().getTipoUtilizador() != TipoUtilizador.ALUNO)
        {
            throw new IdInvalidoException("O ID introduzido nao é um aluno.");
        }

        ProjetoDTO projetoDTO = createProject(propostaID, orientadorID, alunoID);

        atualizarProposta(proposta.get());

        return projetoDTO;
    }

    /**
     * Criar um projeto a partir de uma proposta.
     * @param propostaID Id da proposta
     * @param orientadorID Id do orientador
     * @param estudanteID Id do estudante
     * @return Projeto criado
     */
    private ProjetoDTO createProject(Long propostaID, Long orientadorID, Long estudanteID)
    {
        ProjetoDTO projetoDTO = projetoDTOFactory.createProjeto(propostaID, orientadorID, estudanteID);

        return projetoRestRepository.create(projetoDTO);
    }

    public ConviteDTO createConvite(ConviteDTO conviteDTO)
    {
        Optional<Proposta> proposta = repository.findById(conviteDTO.getIdProposta());
        if (proposta.isEmpty())
        {
            throw new OptionalVazioException("Não existe esta proposta");
        }

        if(proposta.get().getEstadoAtual() != PropostaEstado.APROVADO)
        {
            throw new ValidacaoInvalidaException("Esta proposta nao esta como aprovado");
        }

        Optional<UtilizadorDTO> alunoDTO = utilizadorRestRepository.findById(conviteDTO.getIdAluno());
        if (alunoDTO.isEmpty())
        {
            throw new OptionalVazioException("O utilizador não existe");
        }

        if (alunoDTO.get().getTipoUtilizador() != TipoUtilizador.ALUNO)
        {
            throw new ValidacaoInvalidaException("O utilizador "+conviteDTO.getIdAluno()+" nao e aluno.");
        }

        try
        {
            if (!propostaCandidaturaRepo.isIncrito(conviteDTO.getIdProposta(), conviteDTO.getIdAluno()))
            {
                throw new ValidacaoInvalidaException("O aluno nao "+conviteDTO.getIdAluno()+" nao esta incrito na proposta "+conviteDTO.getIdProposta());
            }
        }
        catch (IdInvalidoException e)
        {
            throw new IdInvalidoException(e.getMessage());
        }

        Optional<UtilizadorDTO> docenteDTO = utilizadorRestRepository.findById(conviteDTO.getIdDocente());
        if (docenteDTO.isEmpty())
        {
            throw new OptionalVazioException("O utilizador não existe");
        }

        if (docenteDTO.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new ValidacaoInvalidaException("O utilizador "+conviteDTO.getIdDocente()+" nao e docente.");
        }

        Convite convite = conviteRepository.createAndSaveConvite(conviteDTOMapper.toModel(conviteDTO));

        return conviteDTOMapper.toDTO(convite);
    }
}