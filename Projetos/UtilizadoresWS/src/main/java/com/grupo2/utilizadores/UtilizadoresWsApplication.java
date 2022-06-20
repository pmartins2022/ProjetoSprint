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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass =  encoder.encode("password");

        return (args) ->
        {
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti@gmail.com", "aluno",
                  pass , TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti1@gmail.com", "utiUsername1",
                    pass, TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti2@gmail.com", "utiUsername2",
                    pass, TipoUtilizador.ALUNO));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti3@gmail.com", "utiUsername3",
                    pass, TipoUtilizador.ALUNO));

            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti4@gmail.com", "docente",
                    pass, TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO( "nome", "sobrenome", "uti5@gmail.com", "utiUsername5",
                    pass, TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO( "nome", "sobrenome", "uti6@gmail.com", "utiUsername6",
                    pass, TipoUtilizador.DOCENTE));
            service.createAndSave(new UtilizadorDTO("nome", "sobrenome", "uti7@gmail.com", "utiUsername7",
                    pass, TipoUtilizador.DOCENTE));

        };
    }
}