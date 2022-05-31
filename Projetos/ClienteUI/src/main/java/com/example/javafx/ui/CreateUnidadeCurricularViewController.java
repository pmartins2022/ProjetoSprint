package com.example.javafx.ui;

import com.example.javafx.controller.UnidadeCurricularController;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class CreateUnidadeCurricularViewController
{
    private UnidadeCurricularController controller;

    public TextField siglaText;
    public TextField denominacaoText;

    public void setController(UnidadeCurricularController controller)
    {
        this.controller = controller;
    }

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

    public void closeWindow(ActionEvent actionEvent)
    {
        siglaText.getScene().getWindow().fireEvent(
                new WindowEvent(siglaText.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
