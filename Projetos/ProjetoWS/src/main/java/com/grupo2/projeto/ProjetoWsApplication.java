package com.grupo2.projeto;

import com.grupo2.projeto.dto.AvaliacaoDTO;
import com.grupo2.projeto.dto.ConteudoDTO;
import com.grupo2.projeto.dto.ProjetoDTO;
import com.grupo2.projeto.model.EstadoConteudo;
import com.grupo2.projeto.repository.jdbc.RepositoryJDBCProjeto;
import com.grupo2.projeto.service.AvaliacaoService;
import com.grupo2.projeto.service.ConteudoService;
import com.grupo2.projeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProjetoWsApplication
{
	@Autowired
	RepositoryJDBCProjeto repositoryJDBCProjeto;

	public static void main(String[] args)
	{
		SpringApplication.run(ProjetoWsApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo()
	{
		return (args) ->
		{
			repositoryJDBCProjeto.count();
		};
	}

/*
	@Bean
	public CommandLineRunner demo(ProjetoService service, AvaliacaoService avaliacaoService, ConteudoService conteudoService)
	{
		return (args) ->
		{
			service.createAndSave(new ProjetoDTO(1L, 1L, 5L));
			service.createAndSave(new ProjetoDTO(2L, 2L, 6L));
			service.createAndSave(new ProjetoDTO(3L, 3L, 7L));
			service.createAndSave(new ProjetoDTO(4L, 4L, 8L));

			avaliacaoService.createAndSave(new AvaliacaoDTO(1L, 5L, 6L, 8L, 1L, 1L, 19));
			avaliacaoService.createAndSave(new AvaliacaoDTO(2L, 8L, 6L, 5L, 2L, 1L, 19));
			avaliacaoService.createAndSave(new AvaliacaoDTO(3L, 7L, 6L, 8L, 3L, 1L, 19));
			avaliacaoService.createAndSave(new AvaliacaoDTO(4L, 8L, 6L, 7L, 4L, 1L, 19));

		   	conteudoService.createAndSave(new ConteudoDTO(1L, 1L, "TITULOASDADS", null, "Texto do documento", "PT", EstadoConteudo.APROVADO));
			conteudoService.createAndSave(new ConteudoDTO(2L, 2L, "TITULOASDADS", null, "Texto do documento", "PT", EstadoConteudo.APROVADO));
			conteudoService.createAndSave(new ConteudoDTO(3L, 3L, "TITULOASDADS", null, "Texto do documento", "PT", EstadoConteudo.APROVADO));
			conteudoService.createAndSave(new ConteudoDTO(4L, 4L, "TITULOASDADS", null, "Texto do documento", "PT", EstadoConteudo.APROVADO));
		};
	}

 */
}