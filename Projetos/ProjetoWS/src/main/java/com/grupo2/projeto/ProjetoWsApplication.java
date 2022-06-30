package com.grupo2.projeto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo2.projeto.dto.*;
import com.grupo2.projeto.model.*;
import com.grupo2.projeto.repository.UtilizadorJDBCRepository;
import com.grupo2.projeto.repository.OrganizacaoJDBCRepository;
import com.grupo2.projeto.repository.jdbc.reflection.TableCreator;
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
	public static void main(String[] args) throws IllegalAccessException
	{
		SpringApplication.run(ProjetoWsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProjetoService service, AvaliacaoService avaliacaoService, ConteudoService conteudoService)
	{
		return (args) ->
		{
			String talUt = TableCreator.generateFromTable(UtilizadorDTO.class);
			String talUC = TableCreator.generateFromTable(UnidadeCurricularDTO.class);
			String talED = TableCreator.generateFromTable(EdicaoUCDTO.class);
			String talPO = TableCreator.generateFromTable(PropostaDTO.class);
			String talPR = TableCreator.generateFromTable(Projeto.class);
			String talCO = TableCreator.generateFromTable(Conteudo.class);
			String talMO = TableCreator.generateFromTable(MomentoAvaliacaoDTO.class);
			String talAV = TableCreator.generateFromTable(Avaliacao.class);
			String talORG = TableCreator.generateFromTable(OrganizacaoDTO.class);
			String talMAN = TableCreator.generateFromTable(AvaliacaoNota.class);

			System.out.println(talORG);
			System.out.println(talUt);
			System.out.println(talUC);

			System.out.println(talED);
			System.out.println(talPO);
			System.out.println(talPR);

			System.out.println(talCO);
			System.out.println(talMO);
			System.out.println(talAV);

			System.out.println(talMAN);

		};
	}

}