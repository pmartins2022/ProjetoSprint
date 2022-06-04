package com.example.javafx.ui.utils;

import javafx.scene.control.Alert;

/**
 * Classe que permite criacao de janelas de alerta de uma forma simples.
 */
public class AlertBuilder
{
    /**
     * Cria um alerta de erro.
     * @param title Titulo da janela.
     * @param type Tipo de alerta.
     * @param header Titulo do cabecalho.
     * @param content Conteudo do alerta.
     */
    public static void showAlert(Alert.AlertType type, String title, String header, String content)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}