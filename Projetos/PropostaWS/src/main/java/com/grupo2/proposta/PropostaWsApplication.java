package com.grupo2.proposta;

import com.grupo2.proposta.dto.ConviteDTO;
import com.grupo2.proposta.dto.PropostaDTO;
import com.grupo2.proposta.jpa.PropostaJPA;
import com.grupo2.proposta.model.EstadoConvite;
import com.grupo2.proposta.model.PropostaEstado;
import com.grupo2.proposta.repository.jpa.PropostaJPARepository;
import com.grupo2.proposta.service.PropostaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
            service.createCandidaturaProposta(new PropostaDTO(1L, 1L, 1L, "TÍTULOASDAS", "PROBLEMAASDASDA",
                    "OBJETIVOASD", 1L, PropostaEstado.CANDIDATURA));
            service.createCandidaturaProposta(new PropostaDTO(2L, 2L, 2L, "TÍTULOASDAS", "PROBLEMAASDASDA",
                    "OBJETIVOASD", 1L, PropostaEstado.APROVADO));
            service.createCandidaturaProposta(new PropostaDTO(3L, 3L, 3L, "TÍTULOASDAS", "PROBLEMAASDASDA",
                    "OBJETIVOASD", 2L, PropostaEstado.APROVADO));
            service.createCandidaturaProposta(new PropostaDTO(4L, 4L, 4L, "TÍTULOASDAS", "PROBLEMAASDASDA",
                    "OBJETIVOASD", 3L, PropostaEstado.CANDIDATURA));


            service.createConvite(new ConviteDTO(1L, 6L, 2L, EstadoConvite.PENDENTE));
            service.createConvite(new ConviteDTO(2L, 7L, 2L, EstadoConvite.PENDENTE));
            service.createConvite(new ConviteDTO(3L, 8L, 3L, EstadoConvite.PENDENTE));
            service.createConvite(new ConviteDTO(4L, 5L, 3L, EstadoConvite.PENDENTE));
            //service.createConvite(new ConviteDTO(1L, 6L, 1L, EstadoConvite.PENDENTE));
        };
    }
}
