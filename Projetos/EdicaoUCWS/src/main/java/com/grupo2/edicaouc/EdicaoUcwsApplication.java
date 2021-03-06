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
		};
	}
}
