package com.example.javafx.controller.aluno;

import com.example.javafx.model.LoginContext;
import org.springframework.stereotype.Controller;

@Controller
public class AlunoController
{
    public String getDTOInfo()
    {
        return LoginContext.getCurrentUser().toString();
    }
}
