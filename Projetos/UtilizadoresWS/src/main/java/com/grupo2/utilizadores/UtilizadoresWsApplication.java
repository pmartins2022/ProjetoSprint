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
//            service.createAndSave(new UtilizadorDTO(1L, "Michael", "Jordan", "mjDOCENTE@hotmail.com", TipoUtilizador.DOCENTE));
//            service.createAndSave(new UtilizadorDTO(2L, "Scottie", "Pippen", "spALUNO@hotmail.com", TipoUtilizador.ALUNO));
//            service.createAndSave(new UtilizadorDTO(3L, "Dennis", "Rodman", "drORIENTADOR@hotmail.com", TipoUtilizador.ORIENTADOR));
        };
    }

}
