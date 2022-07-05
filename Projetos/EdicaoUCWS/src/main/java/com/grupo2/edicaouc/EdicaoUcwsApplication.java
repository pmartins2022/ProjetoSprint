package com.grupo2.edicaouc;

import com.grupo2.edicaouc.dto.AnoLetivoDTO;
import com.grupo2.edicaouc.dto.EdicaoUCDTO;
import com.grupo2.edicaouc.dto.UnidadeCurricularDTO;
import com.grupo2.edicaouc.model.AnoLetivo;
import com.grupo2.edicaouc.service.AnoLetivoService;
import com.grupo2.edicaouc.service.EdicaoUCService;
import com.grupo2.edicaouc.service.UnidadeCurricularService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EdicaoUcwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdicaoUcwsApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(EdicaoUCService edicaoUCService, AnoLetivoService anoLetivoService, UnidadeCurricularService ucService)
	{
		return (args) ->
		{
			ucService.createAndSaveUnidadeCurricular(new UnidadeCurricularDTO("MTA", "denominacao"));
			ucService.createAndSaveUnidadeCurricular(new UnidadeCurricularDTO("PTA", "denominacao"));
			ucService.createAndSaveUnidadeCurricular(new UnidadeCurricularDTO("QMA", "denominacao"));
			ucService.createAndSaveUnidadeCurricular(new UnidadeCurricularDTO("ZTA", "denominacao"));

			anoLetivoService.createAndSaveAnoLetivo(new AnoLetivoDTO("2001-2002"));
			anoLetivoService.createAndSaveAnoLetivo(new AnoLetivoDTO("2002-2003"));
			anoLetivoService.createAndSaveAnoLetivo(new AnoLetivoDTO("2003-2004"));
			anoLetivoService.createAndSaveAnoLetivo(new AnoLetivoDTO("2004-2005"));
			anoLetivoService.createAndSaveAnoLetivo(new AnoLetivoDTO("2005-2006"));

			edicaoUCService.createEdicaoUC(new EdicaoUCDTO("MTA", "2001-2002", 5L));
			edicaoUCService.createEdicaoUC(new EdicaoUCDTO("PTA", "2001-2002", 5L));
			edicaoUCService.createEdicaoUC(new EdicaoUCDTO("QMA", "2001-2002", 5L));
			edicaoUCService.createEdicaoUC(new EdicaoUCDTO("ZTA", "2001-2002", 5L));
		};
	}
	 */
}
