package com.grupo2.utilizadores;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
import com.grupo2.utilizadores.model.TipoUtilizador;
import com.grupo2.utilizadores.model.Utilizador;
import com.grupo2.utilizadores.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UtilizadoresWsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(UtilizadoresWsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UtilizadorService service)
    {
        return (args) ->
        {
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti@gmail.com", "aluno",
                    "password", TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti1@gmail.com", "utiUsername1",
                    "password", TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti2@gmail.com", "utiUsername2",
                    "password", TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti3@gmail.com", "utiUsername3",
                    "password", TipoUtilizador.ALUNO));

            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti4@gmail.com", "docente",
                    "password", TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO( "nome", "sobrenome", "uti5@gmail.com", "utiUsername5",
                    "password", TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO( "nome", "sobrenome", "uti6@gmail.com", "utiUsername6",
                    "password", TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti7@gmail.com", "utiUsername7",
                    "password", TipoUtilizador.DOCENTE));

        };
    }
}