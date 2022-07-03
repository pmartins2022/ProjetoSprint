package com.example.javafx.repository.rest;

import com.example.javafx.dto.ProjetoDTO;
import com.example.javafx.dto.ConviteDTO;
import com.example.javafx.dto.PropostaCandidaturaDTO;
import com.example.javafx.dto.PropostaCandidaturaIDDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.RestPostException;
import com.example.javafx.model.LoginContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Classe que permite a comunicação com WebService externo de Proposta.
 */
@Repository
public class PropostaRestRepo
{

    /**
     * Tentar criar uma proposta.
     *
     * @param propostaDTO Proposta a criar.
     * @return Proposta criada.
     * @throws RestPostException Erro ao criar proposta.
     */
    public PropostaDTO createProposta(PropostaDTO propostaDTO) throws RestPostException
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/create").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(propostaDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: " + e.getMessage());
        }
    }

    /**
     * Encontrar propostas pelo estado atual
     *
     * @param estado o estado
     * @return a lista de propostas
     */
    public List<PropostaDTO> findAllPropostaByEstadoAtual(Integer estado)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/?estado=" + estado)
                    .get().header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<PropostaDTO>>()
            {
            }).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Criar um novo convite
     *
     * @param conviteDTO informacao do convite
     * @return convite criado
     */
    public ConviteDTO createAndSaveConvite(ConviteDTO conviteDTO)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/convite/create").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(conviteDTO)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ConviteDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Encontrar proposta pelo estado e pelo id do aluno
     *
     * @return a proposta
     */
    public PropostaCandidaturaDTO findByEstadoAndAlunoid()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/propostaAluno").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Aceitar uma candidatura a proposta
     * @param idProposta id da proposta
     * @return a proposta
     */
    public PropostaDTO acceptCandidaturaProposta(Long idProposta)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarCandidatura/" + idProposta).put()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Rejeitar uma candidatura a proposta
     * @param idProposta id da proposta
     * @return proposta
     */
    public PropostaDTO rejectCandidaturaProposta(Long idProposta)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/rejeitarCandidatura/" + idProposta).put()
                    .header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Aceitar uma proposta
     * @param idProposta id da proposta
     * @param alunoID id do aluno
     */
    public void acceptProposta(Long idProposta, Long alunoID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarProposta/" + idProposta
                            + "?aluno=" + alunoID).post()
                    .header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            responseSpec.toBodilessEntity().block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Rejeitar uma proposta
     * @param idProposta id da proposta
     * @param alunoID id do aluno
     * @return informacao da rejeicao
     */
    public boolean rejectProposta(Long idProposta, Long alunoID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/rejeitarProposta" + idProposta).post()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return true;
            // return responseSpec.bodyToMono().block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Obter todos os convites ativos
     * @return lista dos convites
     */
    public List<ConviteDTO> getConvites()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/convite/findAtivos").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<ConviteDTO>>()
            {
            }).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Encontrar todos os convites aceites
     * @return lista de convites
     */
    public List<ConviteDTO> findAllConviteAccepted()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/convite/accepted").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(new ParameterizedTypeReference<List<ConviteDTO>>()
            {
            }).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Aceitar orientacao
     * @param conviteDTO informacao do convite
     * @return informacao do convite
     */
    public ConviteDTO aceitarOrientacao(ConviteDTO conviteDTO)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/convite/aceitarOrientacao").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(conviteDTO)).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ConviteDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Rejeitar orientacao
     * @param conviteDTO informacao do convite
     * @return informacao do convite
     */
    public ConviteDTO rejeitarOrientacao(ConviteDTO conviteDTO)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/convite/rejeitarOrientacao").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(conviteDTO)).retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ConviteDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    /**
     * Como aluno, candidatar a uma proposta
     * @param propostaID id da proposta
     * @return informacao da candidatura
     */
    public PropostaCandidaturaDTO alunoCandidaturaProposta(Long propostaID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/candidatarAlunoProposta/" + propostaID).post()
                    .header("Authorization", LoginContext.getToken())
                    .retrieve();

            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        } catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}
