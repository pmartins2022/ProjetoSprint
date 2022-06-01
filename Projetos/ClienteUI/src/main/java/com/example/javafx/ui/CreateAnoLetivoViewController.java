package com.example.javafx.ui;

import com.example.javafx.controller.AnoLetivoController;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

@Controller
public class CreateAnoLetivoViewController
{
    private AnoLetivoController controller;

    @FXML
    public TextField siglaText;

    public void setController(AnoLetivoController controller)
    {
        this.controller = controller;
    }

    public void createAnoLetivo(ActionEvent actionEvent)
    {
        if (siglaText.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Sigla");
            return;
        }

        try
        {
            final AnoLetivoDTO anoLetivo = controller.createAnoLetivo(new AnoLetivoDTO(siglaText.getText()));
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Ano Letivo criado com sucesso. " + anoLetivo.toString());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void closeWindow(ActionEvent actionEvent)
    {
        siglaText.getScene().getWindow().fireEvent(
                new WindowEvent(siglaText.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
