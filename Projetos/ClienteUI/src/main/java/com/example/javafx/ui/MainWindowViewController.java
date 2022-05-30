package com.example.javafx.ui;

import com.example.javafx.controller.AnoLetivoController;
import com.example.javafx.controller.MainWindowController;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainWindowViewController
{
    @FXML
    public MenuItem closeItem;
    @FXML
    public MenuItem aboutItem;
    @FXML
    public MenuItem putTestItem;

    @Autowired
    private AnoLetivoController anoLetivoController;

    @FXML
    public void initialize()
    {
        closeItem.setOnAction(event -> Platform.exit());
        aboutItem.setOnAction(event -> AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sobre", "UI Client", "Teste software JavaFX com Spring."));
        putTestItem.setOnAction(event ->
        {
            try
            {
                AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Recebeu feedback", "Feedback from sv:", anoLetivoController.putTest(new AnoLetivoDTO("bom dia test"), 99, 10));
            } catch (ErrorDetail e)
            {
                AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro: " + e.getStatus(), e.getTitle(), e.getDetail());
            }
        });
    }

    public void createAnoLetivoWindow(ActionEvent actionEvent) throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("Create Ano Letivo");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createAnoLetivo-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        fxmlLoader.<CreateAnoLetivoViewController>getController().setController(anoLetivoController);

        stage.show();
    }
}