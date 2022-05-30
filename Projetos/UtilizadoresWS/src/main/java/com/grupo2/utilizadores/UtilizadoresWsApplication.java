package com.grupo2.utilizadores;

import com.grupo2.utilizadores.dto.UtilizadorDTO;
import com.grupo2.utilizadores.jpa.UtilizadorJPA;
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
            service.createAndSave(new UtilizadorDTO(1, "Michael", "Jordan", "mjDOCENTE@hotmail.com", Utilizador.TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO(2, "Michael", "Jordan", "mjALUNO@hotmail.com", Utilizador.TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO(3, "Michael", "Jordan", "mjORIENTADOR@hotmail.com", Utilizador.TipoUtilizador.ORIENTADOR));
        };
    }



}
