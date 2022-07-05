package com.example.javafx.controller.aluno;

import org.springframework.stereotype.Controller;
import com.example.javafx.model.LoginContext;


@Controller
public class AlunoController
{
    public String getDTOInfo()
    {
        return LoginContext.getCurrentUser().toString();
    }
}
