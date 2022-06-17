package com.example.javafx.ui.utils;

import com.example.javafx.ui.LoginViewController;
import javafx.stage.Stage;

public class JavaFXUtils
{
    private static LoginViewController view;

    public static void setJanelaLogin(LoginViewController janela)
    {
        JavaFXUtils.view = janela;
    }

    public static void aparecerJanelaLogin()
    {
        Stage thisStage = (Stage) view.username.getScene().getWindow();
        thisStage.show();
    }

}
