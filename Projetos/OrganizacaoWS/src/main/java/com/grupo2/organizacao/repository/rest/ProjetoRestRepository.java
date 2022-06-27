package com.grupo2.organizacao.repository.rest;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.exception.ErrorDetail;
import com.grupo2.organizacao.security.LoginContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ProjetoRestRepository
{
    public void saveOrganizacao(OrganizacaoDTO organizacaoDTO)
    {
        System.out.println(LoginContext.getToken());
        WebClient.ResponseSpec spec = WebClient.builder().baseUrl("http://localhost:8083/tabelas/organizacao" ).
                build().post().header("Authorization", LoginContext.getToken())
                .body(BodyInserters.fromValue(organizacaoDTO)).retrieve();

        spec.toBodilessEntity().block();

        spec.onStatus(HttpStatus::is4xxClientError,
                clientResponse -> clientResponse.bodyToMono(ErrorDetail.class));

    }

}
