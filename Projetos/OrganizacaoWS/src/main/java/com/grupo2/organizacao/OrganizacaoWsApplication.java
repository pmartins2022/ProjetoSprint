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
			if (service.findAll().size() > 0) return;

			service.createAndSave(new OrganizacaoDTO(500_000_005));
            service.createAndSave(new OrganizacaoDTO(500_000_010));
			service.createAndSave(new OrganizacaoDTO(500_000_015));
			service.createAndSave(new OrganizacaoDTO(500_000_020));
			service.createAndSave(new OrganizacaoDTO(500_000_025));
			service.createAndSave(new OrganizacaoDTO(500_000_030));
			service.createAndSave(new OrganizacaoDTO(500_000_035));
		};}
}
