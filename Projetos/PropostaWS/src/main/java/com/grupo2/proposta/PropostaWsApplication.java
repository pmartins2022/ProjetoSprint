package com.grupo2.proposta;

import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PropostaWsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PropostaWsApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(PropostaService service)
    {
        return (args) ->
        {
            service.createProposta(new PropostaDTO(1L,1L,1L,"Titulo","Problema","Objetivo",1L,PropostaEstado.APROVADO));
      };
    }
}
