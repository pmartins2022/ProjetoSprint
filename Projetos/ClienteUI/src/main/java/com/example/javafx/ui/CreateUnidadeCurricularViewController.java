package com.example.javafx.ui;

import com.example.javafx.controller.admin.UnidadeCurricularController;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

@Controller

/**
 * Classe controller FXML para criar uma unidade curricular.
 */
public class CreateUnidadeCurricularViewController
{
    private UnidadeCurricularController controller;

    public TextField siglaText;
    public TextField denominacaoText;

    /**
     * Necessario para inicializar o controller FXML.
     * @param controller Controller de unidade curricular.
     */
    public void setController(UnidadeCurricularController controller)
    {
        this.controller = controller;
    }

    /**
     * Criar uma unidade curricular.
     * @param actionEvent informacao do evento.
     */
    public void createUnidadeCurricular(ActionEvent actionEvent)
    {
        if (siglaText.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Sigla");
            return;
        }

        if (denominacaoText.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Denominação");
            return;
        }

        try
        {
            final UnidadeCurricularDTO unidadeCurricular = controller.createUnidadeCurricular
                    (new UnidadeCurricularDTO(siglaText.getText(), denominacaoText.getText()));
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Unidade Curricular criado com sucesso. " + unidadeCurricular.toString());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+e.getStatus(), e.getTitle(), e.getDetail());
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    /**
     * Fecha a janela.
     * @param actionEvent informacao do evento.
     */
    public void closeWindow(ActionEvent actionEvent)
    {
        siglaText.getScene().getWindow().fireEvent(
                new WindowEvent(siglaText.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
