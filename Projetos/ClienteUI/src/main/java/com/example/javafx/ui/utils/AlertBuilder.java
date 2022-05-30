package com.example.javafx.ui.utils;

import javafx.scene.control.Alert;

public class AlertBuilder
{
    public static void showAlert(Alert.AlertType type, String title, String header, String content)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}