package com.example.javafx.ui;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;

public class CreatePropostaViewController
{
    private PropostaController controller;

    @FXML
    public TextField problemaText;

    @FXML
    public TextField objetivoText;

    @FXML
    public TextField tituloText;

    @FXML
    public ChoiceBox<String> organizacaoChoice;

    @FXML
    public ChoiceBox<String> edicaoChoice;

    @FXML
    public TextField userIdText;

    public void initialize(PropostaController controller)
    {
        this.controller = controller;

        try
        {
            organizacaoChoice.getItems().addAll(controller.findAllOrganizacao());

            edicaoChoice.getItems().addAll(controller.findAllEdicao());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+e.getStatus(), e.getTitle(), e.getDetail());
            closeWindow(null);
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
            closeWindow(null);
        }
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
        }
    }

    public void createProposta(ActionEvent actionEvent)
    {
        PropostaDTO dto = controller.createProposta(userIdText,organizacaoChoice.getSelectionModel().getSelectedIndex(),edicaoChoice.getSelectionModel().getSelectedIndex(),tituloText,problemaText,objetivoText);
    }

    public void closeWindow(ActionEvent actionEvent)
    {
        userIdText.getScene().getWindow().fireEvent(
                new WindowEvent(userIdText.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}