package com.grupo2.clientui.config;

import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

public class WebClientConfig
{
    public static WebClient buildWebClient(String port)
    {
        return WebClient.builder()
                .baseUrl("http://localhost:" + port)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:" + port))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();
    }
}

