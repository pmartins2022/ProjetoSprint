package com.example.javafx.config;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * Classe de evento para dar inicio ao java fx.
 */
public class StageReadyEvent extends ApplicationEvent
{
    public Stage getStage()
    {
        return (Stage) getSource();
    }

    public StageReadyEvent(Stage source)
    {
        super(source);
    }
}