package com.grupo2.organizacao;

import com.grupo2.organizacao.dto.OrganizacaoDTO;
import com.grupo2.organizacao.service.OrganizacaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrganizacaoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizacaoWsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(OrganizacaoService service)
	{
		return (args) ->
		{
			service.createAndSave(new OrganizacaoDTO("Escola Secundária Infanta D. Maria",600020150));
			service.createAndSave(new OrganizacaoDTO("Escola Secundária Alves Martins",600012417));
			service.createAndSave(new OrganizacaoDTO("Escola Básica e Secundária Clara de Resende",600080897));
			service.createAndSave(new OrganizacaoDTO("Escola Básica e Secundária Henrique Sommer", 600077462));
		};
	}
}
