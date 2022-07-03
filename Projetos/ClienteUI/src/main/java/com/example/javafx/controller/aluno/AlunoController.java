package com.example.javafx.controller.aluno;

import org.springframework.stereotype.Controller;
import com.example.javafx.model.LoginContext;

/**
 * Classe controller para aluno
 */
@Controller
public class AlunoController
{
    /**
     * Obter informacao do aluno atual na aplicacao
     * @return informacao
     */
    public String getDTOInfo()
    {
        return LoginContext.getCurrentUser().toString();
    }
}
