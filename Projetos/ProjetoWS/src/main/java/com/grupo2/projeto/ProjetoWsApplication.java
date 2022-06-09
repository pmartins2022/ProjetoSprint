package com.grupo2.projeto;

import com.grupo2.projeto.dto.MomentoAvaliacaoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.service.MomentoAvaliacaoService;
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
	public CommandLineRunner demo(ProjetoService projetoService, MomentoAvaliacaoService momentoAvaliacaoService)
	{
		return (args) ->
		{
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
//			service.createAndSave(new ProjetoDTO(2L, 3L, 4L));
			momentoAvaliacaoService.createAndSave(new MomentoAvaliacaoDTO(1L,1L,2L,3L,4L));
			momentoAvaliacaoService.createAndSave(new MomentoAvaliacaoDTO(2L,2L,5L,6L,4L));
		};
	}
}