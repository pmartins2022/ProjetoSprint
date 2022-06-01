package com.grupo2.anoLetivoWS;

import com.grupo2.anoLetivoWS.dto.AnoLetivoDTO;
import com.grupo2.anoLetivoWS.service.AnoLetivoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnoLetivoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnoLetivoWsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AnoLetivoService service)
	{
		return (args) ->
		{
			service.createAndSaveAnoLetivo(new AnoLetivoDTO("2001-2002"));
			service.createAndSaveAnoLetivo(new AnoLetivoDTO("2004-2005"));
		};
	}

}
