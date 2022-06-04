package com.example.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicacao.
 */
@SpringBootApplication
public class SpringFxApplication
{
    public static void main (String[] args)
    {
        Application.launch(JavafxApplication.class,args);
    }
}