package com.grupo2.projeto;

import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.service.ProjetoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoWsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProjetoService service)
	{
		return (args) ->
		{
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
		};
	}
}