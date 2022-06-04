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

/**
 * Classe controller FXML para criar uma edicao de UC.
 */
@Controller
public class CreateEdicaoUCViewController
{
    private EdicaoUCController controller;
    @FXML
    public ChoiceBox<String> txtChoiceBoxAnoLetivo;
    @FXML
    public ChoiceBox<String> txtChoiceBoxUC;

    /**
     * Necessario para inicializar o controller FXML.
     * @param controller Controller de edicao de UC.
     */
    public void setController(EdicaoUCController controller)
    {
        this.controller = controller;

        initializeChoiceBoxs();
    }

    /**
     * Metodo de inicializacao dos dados necessarios para criar uma edicao de UC.
     */
    public void initializeChoiceBoxs()
    {
        try
        {
            txtChoiceBoxUC.getItems().addAll(controller.findAllUC());
            txtChoiceBoxAnoLetivo.getItems().addAll(controller.findAllAnoLetivo());

            txtChoiceBoxUC.getSelectionModel().selectFirst();
            txtChoiceBoxAnoLetivo.getSelectionModel().selectFirst();
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
            closeWindow(null);
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro fatal", "Erro fatal", e.getMessage());
            closeWindow(null);
        }
    }

    /**
     * Cria uma edicao de UC.
     * @param actionEvent Informacao do evento.
     */
    public void createEdicaoUC(ActionEvent actionEvent)
    {
        try
        {
            EdicaoUCDTO edicaoUCDTO = controller.createEdicaoUC(txtChoiceBoxUC.getSelectionModel().getSelectedIndex(), txtChoiceBoxAnoLetivo.getSelectionModel().getSelectedIndex());
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Edição de Unidade Curricular criada com sucesso. " + edicaoUCDTO.toString());
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    /**
     * Fecha a janela.
     * @param actionEvent Informacao do evento.
     */
    public void closeWindow(ActionEvent actionEvent)
    {
        txtChoiceBoxUC.getScene().getWindow().fireEvent(
                new WindowEvent(txtChoiceBoxUC.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
