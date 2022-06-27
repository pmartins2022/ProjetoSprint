package com.grupo2.proposta.service;

import com.grupo2.proposta.dto.*;
import com.grupo2.proposta.dto.factory.ProjetoDTOFactory;
import com.grupo2.proposta.dto.mapper.ConviteDTOMapper;
import com.grupo2.proposta.dto.mapper.PropostaCandidaturaDTOMapper;
import com.grupo2.proposta.dto.mapper.PropostaDTOMapper;
import com.grupo2.proposta.exception.*;
import com.grupo2.proposta.model.*;
import com.grupo2.proposta.model.factory.ConviteIDFactory;
import com.grupo2.proposta.repository.ConviteRepository;
import com.grupo2.proposta.repository.PropostaCandidaturaRepo;
import com.grupo2.proposta.repository.PropostaRepository;
import com.grupo2.proposta.repository.rest.EdicaoUCRestRepository;
import com.grupo2.proposta.repository.rest.OrganizacaoRestRepository;
import com.grupo2.proposta.repository.rest.ProjetoRestRepository;
import com.grupo2.proposta.repository.rest.UtilizadorRestRepository;
import com.grupo2.proposta.security.LoginContext;
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
    /**
     * Objeto do tipo PropostaRepository a ser utilizador por PropostaService
     */
    @Autowired
    private PropostaRepository repository;
    /**
     * Objeto do tipo UtilizadorRestRepository a ser utilizador por PropostaService
     */
    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;
    /**
     * Objeto do tipo ProjetoRestRepository a ser utilizador por PropostaService
     */
    @Autowired
    private ProjetoRestRepository projetoRestRepository;
    /**
     * Objeto do tipo PropostaCandidaturaRepo a ser utilizador por PropostaService
     */
    @Autowired
    private PropostaCandidaturaRepo propostaCandidaturaRepo;
    /**
     * Objeto do tipo PropostaCandidaturaDTOMapper a ser utilizador por PropostaService
     */
    @Autowired
    private PropostaCandidaturaDTOMapper candidaturaDTOMapper;
    /**
     * Objeto do tipo PropostaDTOMapper a ser utilizador por PropostaService
     */
    @Autowired
    private PropostaDTOMapper mapper;
    /**
     * Objeto do tipo ProjetoDTOFactory a ser utilizador por PropostaService
     */
    @Autowired
    private ProjetoDTOFactory projetoDTOFactory;
    /**
     * Objeto do tipo EdicaoUCRestRepository a ser utilizador por PropostaService
     */
    @Autowired
    private EdicaoUCRestRepository edicaoUCRestRepository;
    /**
     * Objeto do tipo OrganizacaoRestRepository a ser utilizador por PropostaService
     */
    @Autowired
    private OrganizacaoRestRepository organizacaoRestRepository;
    /**
     * Objeto do tipo ConviteRepository a ser utilizador por PropostaService
     */
    @Autowired
    private ConviteRepository conviteRepository;
    /**
     * Objeto do tipo ConviteDTOMapper a ser utilizador por PropostaService
     */
    @Autowired
    private ConviteDTOMapper conviteDTOMapper;
    /**
     * Objeto do tipo ConviteIDFactory a ser utilizador por PropostaService
     */
    @Autowired
    private ConviteIDFactory conviteIDFactory;

    /**
     * Criar uma nova candidatura a proposta.
     *
     * @param dto Proposta a ser criada
     * @return Proposta criada
     * @throws BaseDadosException Se ocorrerem erros relativos a base de dados
     */
    public PropostaDTO createCandidaturaProposta(PropostaDTO dto) throws BaseDadosException
    {
        Proposta proposta = mapper.toModel(dto);

        Optional<UtilizadorDTO> utilizadorId = utilizadorRestRepository.findById(proposta.getUtilizadorId());
        if (utilizadorId.isEmpty())
        {
            throw new BaseDadosException("Id de utilizador " + proposta.getUtilizadorId() + " nao existe.");
        }

        Optional<OrganizacaoDTO> organizacaoId = organizacaoRestRepository.findById(proposta.getOrganizacaoId());

        if (organizacaoId.isEmpty())
        {
            throw new BaseDadosException("Id de organizacao " + proposta.getOrganizacaoId() + " nao existe.");
        }

        Optional<EdicaoUCDTO> edicaoId = edicaoUCRestRepository.findById(proposta.getEdicaoUCId());

        if (edicaoId.isEmpty())
        {
            throw new BaseDadosException("Id de edicao unidade curricular " + proposta.getEdicaoUCId() + " nao existe.");
        }

        Proposta prop = repository.save(proposta);

        return mapper.toDTO(prop);
    }

    /**
     * Rejeitar uma proposta.
     *
     * @param id Id da proposta a ser rejeitada
     * @return Proposta rejeitada ou erro se a atualizacao for invalida
     */
    public Optional<PropostaDTO> rejeitarCandidaturaProposta(Long id)
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

    /**
     * Método que permite atualizar uma Proposta
     * @param proposta proposta atualizada
     * @return PropostaDTO ou Optional.empty()
     */
    public Optional<PropostaDTO> atualizarProposta(Proposta proposta)
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
     *
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
     *
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
     *
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
     * Devolve PropostaDTO APROVADA
     * @param idDocente id de docente
     * @param idProposta id de proposta
     * @return PropostaDTO
     */
    public PropostaDTO acceptCandidaturaProposta(Long idDocente, Long idProposta)
    {
        List<EdicaoUCDTO> rucEdicaoList = edicaoUCRestRepository.findByRucID(idDocente);

        if (rucEdicaoList.isEmpty())
        {
            throw new OptionalVazioException(idDocente + " não é um RUC.");
        }

        Optional<Proposta> proposta = repository.findById(idProposta);

        if (proposta.isEmpty())
        {
            throw new OptionalVazioException("Candidatura a proposta com id " + idProposta + " não existe.");
        }

        boolean found = false;
        for (EdicaoUCDTO edicao : rucEdicaoList)
        {
            if (edicao.getId().equals(proposta.get().getEdicaoUCId()))
            {
                found = true;
                break;
            }
        }

        if (!found)
        {
            throw new OptionalVazioException("Candidatura a proposta com id " + idProposta +
                    " não pertence ao RUC com id " + idDocente + ".");
        }

        if (proposta.get().getEstadoAtual().ordinal() != 0) //CANDIDATURA
        {
            throw new ValidacaoInvalidaException("Candidatura a proposta tem que estar em fase de CANDIDATURA" +
                    idProposta + " encontra-se em fase de " + proposta.get().getEstadoAtual().name());
        }

        proposta.get().setEstadoAtual(PropostaEstado.APROVADO);

        Proposta saved = repository.save(proposta.get());

        return mapper.toDTO(saved);
    }

    /**
     * Devolve Convite ACEITE
     * @param conviteDTO convite a aceitar
     * @param encoded credencias do Utilizador com login efetuado
     * @return ConviteDTO
     * @throws IdInvalidoException caso ocorra um erro nas validações de IDs
     */
    public ConviteDTO acceptOrientacaoProposta(ConviteDTO conviteDTO,
                                               String encoded) throws IdInvalidoException
    {
        Optional<UtilizadorDTO> orientadorDTO = utilizadorRestRepository.findById(conviteDTO.getIdDocente());
        if (orientadorDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Orientador " + conviteDTO.getIdDocente() + " nao existe.");
        } else if (orientadorDTO.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new IdInvalidoException("O ID introduzido nao é um docente.");
        }

        ConviteID conviteId = conviteIDFactory.create(conviteDTO.getIdAluno(),conviteDTO.getIdDocente(),conviteDTO.getIdProposta());

        Optional<Convite> convite = conviteRepository.findById(conviteId);
        if (convite.isEmpty())
        {
            throw new IdInvalidoException("O docente " + conviteDTO.getIdDocente() + " não tem nenhum convite para a proposta " + conviteDTO.getIdProposta());
        }
        if (convite.get().getEstado() != EstadoConvite.PENDENTE)
        {
            throw new AtualizacaoInvalidaException("Nao pode aceitar este convite");
        }

        convite.get().setEstado(EstadoConvite.ACEITE);

        conviteRepository.atualizar(convite.get());

        var value = convite.get();

        return conviteDTOMapper.toDTO(value);
    }

    /**
     * Devolve ConviteDTO RECUSADO
     * @param conviteDTO conviteDTO a recusar
     * @param encoded credencias de Utilizador com login efetuado
     * @return ConviteDTO
     * @throws IdInvalidoException caso ocorra um erro nas validações de IDs
     */
    public ConviteDTO rejectOrientacaoProposta(ConviteDTO conviteDTO, String encoded) throws IdInvalidoException
    {
        Optional<UtilizadorDTO> orientadorDTO = utilizadorRestRepository.findById(conviteDTO.getIdDocente());
        if (orientadorDTO.isEmpty())
        {
            throw new IdInvalidoException("Id de Orientador " + conviteDTO.getIdDocente() + " nao existe.");
        } else if (orientadorDTO.get().getTipoUtilizador() != TipoUtilizador.DOCENTE)
        {
            throw new IdInvalidoException("O ID introduzido nao é um docente.");
        }

        ConviteID conviteId = conviteIDFactory.create(conviteDTO.getIdAluno(),conviteDTO.getIdDocente(),conviteDTO.getIdProposta());

        Optional<Convite> convite = conviteRepository.findById(conviteId);
        if (convite.isEmpty())
        {
            throw new IdInvalidoException("O docente " + conviteDTO.getIdDocente() + " não tem nenhum convite para a proposta " + conviteDTO.getIdProposta());
        }
        if (convite.get().getEstado() != EstadoConvite.PENDENTE)
        {
            throw new AtualizacaoInvalidaException("Nao pode aceitar este convite");
        }

        convite.get().setEstado(EstadoConvite.RECUSADO);

        conviteRepository.atualizar(convite.get());

        var value = convite.get();

        return conviteDTOMapper.toDTO(value);
    }

    /**
     * Criar um projeto a partir de uma proposta.
     *
     * @param propostaID   Id da proposta
     * @param orientadorID Id do orientador
     * @param estudanteID  Id do estudante
     * @return Projeto criado
     */
    public ProjetoDTO createProject(Long propostaID, Long orientadorID, Long estudanteID)
    {
        ProjetoDTO projetoDTO = projetoDTOFactory.createProjeto(propostaID, estudanteID,orientadorID);

        return projetoRestRepository.create(projetoDTO);
    }

    /**
     * Devolve ConviteDTO PENDENTE
     * @param conviteDTO conviteDTO a criar
     * @return ConviteDTO
     */
    public ConviteDTO createConvite(ConviteDTO conviteDTO)
    {
        Optional<Proposta> proposta = repository.findById(conviteDTO.getIdProposta());
        if (proposta.isEmpty())
        {
            throw new OptionalVazioException("Não existe esta proposta");
        }

        if (proposta.get().getEstadoAtual() != PropostaEstado.APROVADO)
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
            throw new ValidacaoInvalidaException("O utilizador " + conviteDTO.getIdAluno() + " nao e aluno.");
        }

        try
        {
            if (!propostaCandidaturaRepo.isIncrito(conviteDTO.getIdProposta(), conviteDTO.getIdAluno()))
            {
                throw new ValidacaoInvalidaException("O aluno " + conviteDTO.getIdAluno() + " nao esta incrito na proposta " + conviteDTO.getIdProposta());
            }
        } catch (IdInvalidoException e)
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
            throw new ValidacaoInvalidaException("O utilizador " + conviteDTO.getIdDocente() + " nao e docente.");
        }

        Convite convite = conviteRepository.createAndSaveConvite(conviteDTOMapper.toModel(conviteDTO));

        return conviteDTOMapper.toDTO(convite);
    }

    /**
     * Devolve PropostaCandidaturaDTO PENDENTE;
     * Candidata aluno a uma proposta
     * @param propostaID id de proposta para onde o Aluno se vai candidatar
     * @return PropostaCandidaturaDTO
     */
    public PropostaCandidaturaDTO candidatarAlunoProposta(Long propostaID)
    {
        Optional<Proposta> proposta = repository.findById(propostaID);
        if (proposta.isEmpty())
        {
            throw new OptionalVazioException("Não existe esta proposta");
        }

        if (proposta.get().getEstadoAtual() != PropostaEstado.APROVADO)
        {
            throw new ValidacaoInvalidaException("Esta proposta nao está como aprovado");
        }

        Long alunoId = LoginContext.getCurrent().getId();

        if (propostaCandidaturaRepo.isAlunoInscrito(alunoId))
        {
            throw new AtualizacaoInvalidaException("O aluno ja se encontra inscrito numa proposta ativa");
        }

        return candidaturaDTOMapper.toDTO(propostaCandidaturaRepo.createAndSave(propostaID, alunoId));
    }

    /**
     * Devolve ProjetoDTO;
     * APROVA Proposta
     * @param propostaID id de proposta a aprovar
     * @param orientadorID id de orientador
     * @param alunoID id de aluno
     * @return ProjetoDTO
     */
    public ProjetoDTO acceptProposta(Long propostaID, Long orientadorID, Long alunoID)
    {
        //encontrar e validar proposta
        Optional<Proposta> proposta = repository.findById(propostaID);
        if (proposta.isEmpty())
        {
            throw new IdInvalidoException("Id de proposta " + propostaID + " nao existe.");
        }

        if (proposta.get().getEstadoAtual() != PropostaEstado.APROVADO)
        {
            throw new ValidacaoInvalidaException("Esta proposta nao esta como aprovado");
        }

        if (proposta.get().getEstadoAtual() == PropostaEstado.EM_PROJETO)
        {
            throw new AtualizacaoInvalidaException("Esta proposta ja esta em projeto");
        }

        //encontrar e validar aluno
        if (!utilizadorRestRepository.isRole("ROLE_ALUNO", alunoID))
        {
            throw new IdInvalidoException("O ID introduzido nao é um aluno.");
        }


        //encontrar o convite especifico
        ConviteID conviteId = conviteIDFactory.create(alunoID, orientadorID, propostaID);
        Optional<Convite> convite = conviteRepository.findById(conviteId);

        if (convite.isEmpty())
        {
            throw new IdInvalidoException("O aluno " + alunoID + " nao tem nenhum convite para esta proposta");
        }

        if (convite.get().getEstado() != EstadoConvite.ACEITE)
        {
            throw new ValidacaoInvalidaException("O convite do aluno nao está em fase ACEITE");
        }


        //atualizar o convite para EM_PROJETO
        convite.get().setEstado(EstadoConvite.EM_PROJETO);
        atualizarConvite(convite.get());

        //colocar esta proposta como em_projeto
        proposta.get().aceitarPropostaProjeto();
        atualizarProposta(proposta.get());

        //invalidar as inscricoes (candidaturas) dos outros alunos a esta proposta
        propostaCandidaturaRepo.invalidarCandidaturas(propostaID, alunoID);

        //invalidar os convites dos outros alunos
        conviteRepository.invalidarConvites(propostaID, alunoID);

        //criar projeto
        return createProject(propostaID, orientadorID, alunoID);
    }

    /**
     * Método que atualiza Convite
     * @param convite convite a atualizar
     */
    private void atualizarConvite(Convite convite)
    {
        conviteRepository.atualizar(convite);
    }

    /**
     * Devolve PropostaCandidaturaDTO ACEITE;
     * Aceita a candidatura de um aluno a uma proposta;
     * @param idUtilizador id de Utilizador com login efetuado
     * @param propostaCandidaturaID id da candidatura à proposta
     * @return PropostaCandidaturaDTO
     */
    public PropostaCandidaturaDTO acceptAlunoCandidaturaProposta(Long idUtilizador, PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        Optional<PropostaCandidatura> propostaCandidatura = propostaCandidaturaRepo.findByID(candidaturaDTOMapper.toModelID(propostaCandidaturaID));

        if (propostaCandidatura.isEmpty())
        {
            System.out.println("Não existe candidatura com estes IDs: " + propostaCandidaturaID.getIdProposta()
                    + " e " + propostaCandidaturaID.getIdAluno());
            throw new OptionalVazioException("Não existe candidatura com estes IDs: " + propostaCandidaturaID.getIdProposta()
                    + " e " + propostaCandidaturaID.getIdAluno());
        }

        if (propostaCandidatura.get().getEstado() != EstadoCandidatura.PENDENTE)
        {
            System.out.println("Candidatura tem que estar em fase " + EstadoCandidatura.PENDENTE +
                    "e encontra-se em fase " + propostaCandidatura.get().getEstado().name());
            throw new ValidacaoInvalidaException("Candidatura tem que estar em fase " + EstadoCandidatura.PENDENTE +
                    "e encontra-se em fase " + propostaCandidatura.get().getEstado().name());
        }

        Optional<EdicaoUCDTO> edicaoUCDTO = edicaoUCRestRepository.findByRucIDAndEstadoEdicaoUC(idUtilizador);

        if (edicaoUCDTO.isEmpty())
        {
            System.out.println(idUtilizador + " não é RUC de nenhuma EdiçãoUC ativa.");
            throw new OptionalVazioException(idUtilizador + " não é RUC de nenhuma EdiçãoUC ativa.");
        }

        List<Proposta> propostaList = repository.findByEdicaoUCId(edicaoUCDTO.get().getId());

        if (propostaList.isEmpty())
        {
            System.out.println(
                    idUtilizador + " não tem propostas na sua EdiçãoUC."
            );
            throw new OptionalVazioException(idUtilizador + " não tem propostas na sua EdiçãoUC.");
        }

        boolean isPropostaInList = false;
        for (Proposta p : propostaList
        )
        {
            if (p.getId().equals(propostaCandidaturaID.getIdProposta()))
            {
                isPropostaInList = true;
                break;
            }
        }

        if (!isPropostaInList)
        {
            System.out.println("Esta proposta não está ao encargo do RUC " + idUtilizador);
            throw new ValidacaoInvalidaException("Esta proposta não está ao encargo do RUC " + idUtilizador);
        }

        propostaCandidatura.get().setEstado(EstadoCandidatura.ACEITE);
        PropostaCandidatura updated = propostaCandidaturaRepo.updateAndSave(propostaCandidatura.get());

        propostaCandidaturaRepo.invalidarCandidaturas(propostaCandidaturaID.getIdProposta(),propostaCandidaturaID.getIdAluno());

        return candidaturaDTOMapper.toDTO(updated);
    }

    /**
     * Devolve PropostaCandidaturaDTO REJEITADA
     * @param idUtilizador id de Utilizador com login efetuado
     * @param propostaCandidaturaID id da candidatura à proposta a ser rejeitada
     * @return PropostaCandidaturaDTO
     */
    public PropostaCandidaturaDTO rejectAlunoCandidaturaProposta(Long idUtilizador, PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        Optional<PropostaCandidatura> propostaCandidatura = propostaCandidaturaRepo.findByID(candidaturaDTOMapper.toModelID(propostaCandidaturaID));

        if (propostaCandidatura.isEmpty())
        {
            throw new OptionalVazioException("Não existe candidatura com estes IDs: " + propostaCandidaturaID.getIdProposta()
                    + " e " + propostaCandidaturaID.getIdAluno());
        }

        if (propostaCandidatura.get().getEstado().ordinal() != 0)
        {
            throw new ValidacaoInvalidaException("Candidatura tem que estar em fase " + EstadoCandidatura.PENDENTE +
                    "e encontra-se em fase " + propostaCandidatura.get().getEstado().name());
        }

        Optional<EdicaoUCDTO> edicaoUCDTO = edicaoUCRestRepository.findByRucIDAndEstadoEdicaoUC(idUtilizador);

        if (edicaoUCDTO.isEmpty())
        {
            throw new OptionalVazioException(idUtilizador + " não é RUC de nenhuma EdiçãoUC ativa.");
        }

        List<Proposta> propostaList = repository.findByEdicaoUCId(edicaoUCDTO.get().getId());

        if (propostaList.isEmpty())
        {
            throw new OptionalVazioException(idUtilizador + " não tem propostas na sua EdiçãoUC.");
        }

        boolean isPropostaInList = false;
        for (Proposta p : propostaList
        )
        {
            if (p.getId().equals(propostaCandidaturaID.getIdProposta()))
            {
                isPropostaInList = true;
                break;
            }
        }

        if (!isPropostaInList)
        {
            throw new ValidacaoInvalidaException("Esta proposta não está ao encargo do RUC " + idUtilizador);
        }


        propostaCandidatura.get().setEstado(EstadoCandidatura.REJEITADO);

        PropostaCandidatura updated = propostaCandidaturaRepo.updateAndSave(propostaCandidatura.get());

        return candidaturaDTOMapper.toDTO(updated);
    }

    /**
     * Devolve Lista de PropostaDTO filtrada pelo Estado
     * @param estado estado da PropostaDTO
     * @return Lista de PropostaDTO
     */
    public List<PropostaDTO> findAllByEstado(Integer estado)
    {
        List<Proposta> list = repository.findAllByEstado(estado);

        return list.stream().map(mapper::toDTO).toList();
    }

    /**
     * Método que rejeita uma Proposta
     * @param propostaID id de proposta a ser rejeitada
     */
    public void rejectProposta(Long propostaID)
    {
        //encontrar e validar proposta
        Optional<Proposta> proposta = repository.findById(propostaID);
        if (proposta.isEmpty())
        {
            throw new IdInvalidoException("Id de proposta " + propostaID + " nao existe.");
        }

        if (proposta.get().getEstadoAtual() != PropostaEstado.CANDIDATURA)
        {
            throw new AtualizacaoInvalidaException("Esta proposta nao pode ser rejeitada. Ja se encontra " + proposta.get().getEstadoAtual().name());
        }

        //invalidar as inscricoes (candidaturas) de todos os alunos a esta proposta
        propostaCandidaturaRepo.invalidarTodasCandidaturas(propostaID);

        //invalidar os convites de todos os alunos
        conviteRepository.invalidarTodosConvites(propostaID);

        proposta.get().reprovarProposta();

        repository.atualizarProposta(proposta.get());
    }

    /**
     * Devolve PropostaCandidaturaDTO filtrada pelo id de aluno e estado ou Optional.empty()
     * @param id id de PropostaCandidaturaDTO
     * @return PropostaCandidaturaDTO ou Optional.empty()
     */
    public Optional <PropostaCandidaturaDTO> findPropostaAtivaByAlunoId(Long id)
    {
        Optional<PropostaCandidatura> propostaCandidatura = propostaCandidaturaRepo.findPropostaAtivaByAlunoId(id, EstadoCandidatura.ACEITE);

        if (propostaCandidatura.isEmpty())
        {
            return Optional.empty();
        }

        return Optional.of(candidaturaDTOMapper.toDTO(propostaCandidatura.get())) ;
    }

    /**
     * Devolve Lista de ConviteDTO em estado PENDENTE
     * @param id id de estado
     * @return Lista de ConviteDTO
     */
    public List<ConviteDTO> findConvitesPendentes(Long id)
    {
        List<Convite> convs = conviteRepository.findConvitesPendentes(id);

        return convs.stream().map(conviteDTOMapper::toDTO).toList();
    }

    /**
     * Devolve Lista de PropostaCandidaturaDTO em estado CANDIDATURA
     * @return Lista de ConviteDTO
     */
    public List<PropostaCandidaturaDTO> findAllCandidaturaProposta()
    {
        return propostaCandidaturaRepo.findAll().stream().map(candidaturaDTOMapper::toDTO).toList();
    }
}