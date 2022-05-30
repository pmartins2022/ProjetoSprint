package com.grupo2.uc;

import com.grupo2.uc.jpa.UnidadeCurricularJPA;
import com.grupo2.uc.repository.jpa.UnidadeCurricularJPARepository;
import com.grupo2.uc.service.UnidadeCurricularService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnidadeCurricularWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnidadeCurricularWsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UnidadeCurricularService service, UnidadeCurricularJPARepository jpaRepo)
	{
		return (args) ->
		{
			jpaRepo.save(new UnidadeCurricularJPA("asdasdasdf", "asdasdasdasfa"));
		};
	}
}
