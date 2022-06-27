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

}
