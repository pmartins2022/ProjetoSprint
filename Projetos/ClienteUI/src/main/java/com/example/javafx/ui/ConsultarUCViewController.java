package com.example.javafx.ui;

import com.example.javafx.controller.admin.UnidadeCurricularController;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

/**
 * Classe controller FXML para consultar unidade curricular.
 */
@Controller
public class ConsultarUCViewController
{

    public ChoiceBox<String> choiceBoxUC;
    public TextArea textUC;

    UnidadeCurricularController controller;

    /**
     * Inicializa o controller FXML.
     */
    public void initialize(UnidadeCurricularController controller)
    {
        this.controller = controller;

        textUC.setEditable(false);

        try
        {
            choiceBoxUC.getItems().addAll(controller.findAllUnidadeCurricular());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR,"Erro "+e.getStatus(),e.getTitle(),e.getDetail());
            closeWindow(null);
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR,"Erro Fatal","Erro Fatal",e.getMessage());
            closeWindow(null);
        }

        choiceBoxUC.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->
        {
            textUC.setText(controller.getFromLista(choiceBoxUC.getSelectionModel().getSelectedIndex()).toStringText());
        }
        );
    }

    /**
     * Fecha a janela.
     * @param actionEvent informacao do evento.
     */
    public void closeWindow(ActionEvent actionEvent)
    {
        textUC.getScene().getWindow().fireEvent(
                new WindowEvent(textUC.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}