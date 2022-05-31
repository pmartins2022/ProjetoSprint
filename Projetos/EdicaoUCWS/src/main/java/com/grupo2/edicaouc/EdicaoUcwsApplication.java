package com.grupo2.edicaouc;

import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.service.EdicaoUCService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EdicaoUcwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdicaoUcwsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EdicaoUCService edicaoService) {
		return (args) -> {
			edicaoService.createEdicaoUC("432","20006-4302");
			edicaoService.createEdicaoUC("2","2002-2003");
			edicaoService.createEdicaoUC("3","2003-2004");
			edicaoService.createEdicaoUC("4","2004-2005");
			edicaoService.createEdicaoUC("4","2012-2014");

		};
	}
}
