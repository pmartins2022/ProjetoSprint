package com.example.javafx.ui;

import com.example.javafx.controller.AnoLetivoController;
import com.example.javafx.controller.PropostaController;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class CreatePropostaViewController
{

    private PropostaController controller;

    public TextField userIdText;
    public TextField orgIdText;
    public TextField edicaoIdText;

    public void setController(PropostaController controller)
    {
        this.controller = controller;
    }

    public void validateNumberField(KeyEvent keyEvent)
    {
        if (!keyEvent.getCharacter().matches("[0-9]"))
        {
            keyEvent.consume();
            if (keyEvent.getSource() == userIdText)
            {
                userIdText.clear();
            }
            else if (keyEvent.getSource() == orgIdText)
            {
                orgIdText.clear();
            }
            else if (keyEvent.getSource() == edicaoIdText)
            {
                edicaoIdText.clear();
            }
        }
    }
}