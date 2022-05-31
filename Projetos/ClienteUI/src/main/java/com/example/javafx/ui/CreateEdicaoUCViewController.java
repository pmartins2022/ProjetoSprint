package com.example.javafx.ui;

import com.example.javafx.controller.EdicaoUCController;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.exception.ListaVaziaException;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

@Controller
public class CreateEdicaoUCViewController
{
    private EdicaoUCController controller;
    @FXML
    public ChoiceBox<AnoLetivoDTO> txtChoiceBoxAnoLetivo;
    @FXML
    public ChoiceBox<UnidadeCurricularDTO> txtChoiceBoxUC;

    public void setController(EdicaoUCController controller)
    {
        this.controller = controller;

        try
        {
            txtChoiceBoxUC.getItems().addAll(controller.findAllUC());

            txtChoiceBoxAnoLetivo.getItems().addAll(controller.findAllAnoLetivo());
        }
        catch(ListaVaziaException e)
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", e.getMessage());
            closeWindow(null);
        }

        txtChoiceBoxUC.getSelectionModel().selectFirst();
        txtChoiceBoxAnoLetivo.getSelectionModel().selectFirst();
    }

    public void createEdicaoUC(ActionEvent actionEvent)
    {
        try
        {
            UnidadeCurricularDTO ucDTO = txtChoiceBoxUC.getSelectionModel().getSelectedItem();
            AnoLetivoDTO anoLetivoDTO = txtChoiceBoxAnoLetivo.getSelectionModel().getSelectedItem();

            final EdicaoUCDTO edicaoUCDTO = controller.createEdicaoUC(ucDTO, anoLetivoDTO);
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Edição de Unidade Curricular criada com sucesso. " + edicaoUCDTO.toString());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+ e.getStatus(), e.getTitle(), e.getDetail());
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void closeWindow(ActionEvent actionEvent)
    {
        txtChoiceBoxUC.getScene().getWindow().fireEvent(
                new WindowEvent(txtChoiceBoxUC.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
