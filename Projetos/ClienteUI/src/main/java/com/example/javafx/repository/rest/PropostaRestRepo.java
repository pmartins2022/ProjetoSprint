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
        }
        catch (RestPostException e)
        {
            throw new RestPostException("Problema no servidor: "+e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }


    public PropostaCandidaturaDTO acceptCandidaturaAlunoProposta(PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarCandidaturaAluno").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(propostaCandidaturaID)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    public PropostaCandidaturaDTO rejectCandidaturaAlunoProposta(PropostaCandidaturaIDDTO propostaCandidaturaID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/rejeitarCandidaturaAluno").post()
                    .header("Authorization", LoginContext.getToken())
                    .body(BodyInserters.fromValue(propostaCandidaturaID)).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaCandidaturaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    public PropostaDTO findByEstadoAndAlunoid()
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/propostaAluno").get()
                    .header("Authorization", LoginContext.getToken()).retrieve();
            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));
            return responseSpec.bodyToMono(PropostaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

public PropostaDTO acceptCandidaturaProposta(Long idProposta)
{
    try
    {
        WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarCandidatura/" + idProposta).post()
                .header("Authorization", LoginContext.getToken()).retrieve();
        
        responseSpec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

        return responseSpec.bodyToMono(PropostaDTO.class).block();
    }
    catch (RestPostException e)
    {
        throw new RestPostException(e.getMessage());
    }
}

    public PropostaDTO rejectCandidaturaProposta(Long idProposta)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/rejeitarCandidatura/" + idProposta).post()
                    .header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(PropostaDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

    public ProjetoDTO acceptProposta(Long idProposta, Long alunoID)
    {
        try
        {
            WebClient.ResponseSpec responseSpec = WebClient.create("http://localhost:8084/proposta/aceitarProposta/" + idProposta
                    + "?aluno=" + alunoID).post()
                    .header("Authorization", LoginContext.getToken()).retrieve();


            responseSpec.onStatus(HttpStatus::is4xxClientError,
                    clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

            return responseSpec.bodyToMono(ProjetoDTO.class).block();
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }

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
        }
        catch (RestPostException e)
        {
            throw new RestPostException(e.getMessage());
        }
    }
}
