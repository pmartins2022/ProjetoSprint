package com.example.javafx.ui.aluno;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.controller.aluno.AlunoController;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import com.example.javafx.ui.utils.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;

@Controller
public class AlunoMainWindowViewController
{
    public TextField userIdText;
    public TextField tituloText;
    public TextField problemaText;
    public TextField objetivoText;
    public ChoiceBox<String> organizacaoChoice;
    public ChoiceBox<String> edicaoChoice;
    public TabPane alunoPane;
    public Button logOutButton;
    public ChoiceBox docenteChoiceBox;
    public TextArea txtPropostaInfo;
    public ChoiceBox propostaChoiceBox;
    public TextArea alunoDTOText;
    public Button createPropostaButton;
    private AlunoController alunoController;
    private PropostaController propostaController;

    @FXML
    public void logOut(ActionEvent event)
    {
        JavaFXUtils.aparecerJanelaLogin();
        closeWindow();
    }

    @FXML
    private void closeWindow()
    {
        logOutButton.getScene().getWindow().fireEvent(
                new WindowEvent(logOutButton.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setController(AlunoController alunoController, PropostaController controller)
    {
        this.alunoController = alunoController;
        this.propostaController = controller;

        alunoPane.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> tabPaneChanged(t1));


        iniciarTabHome();
    }

    /**
     * Necessario para inicializar o controller FXML.
     * @param controller Controller de proposta.
     */
    public void initialize(PropostaController controller)
    {
        this.propostaController = controller;

        try
        {
            organizacaoChoice.getItems().addAll(controller.findAllOrganizacao());

            edicaoChoice.getItems().addAll(controller.findAllEdicao());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+e.getStatus(), e.getTitle(), e.getDetail());
            e.printStackTrace();
            closeWindow();
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
            e.printStackTrace();
            closeWindow();
        }
    }

    /**
     * Validar uma caixa de texto para permitir apenas numeros inteiros.
     * @param keyEvent Informacao da tecla.
     */
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

    /**
     * Cria uma proposta.
     * @param actionEvent Informacao do evento.
     */
    public void createProposta(ActionEvent actionEvent)
    {
        try
        {
            PropostaDTO dto = propostaController.createProposta(Long.parseLong(userIdText.getText()), organizacaoChoice.getSelectionModel().getSelectedIndex()+1, edicaoChoice.getSelectionModel().getSelectedIndex()+1, tituloText.getText(), problemaText.getText(), objetivoText.getText());
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Proposta criada com sucesso. " + dto.toString());
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

    public void createConvite(ActionEvent actionEvent)
    {
    }

    public void createCandidatura(ActionEvent actionEvent)
    {
    }

    public void tabPaneChanged(Number t1)
    {
        System.out.println("Tab pane selecionado: "+t1);

        switch (t1.intValue())
        {
            case 0 -> iniciarTabHome();
            case 1 -> iniciarTabCriarProposta();
        }
    }

    private void iniciarTabHome()
    {
        alunoDTOText.setText(alunoController.getDTOInfo());
    }

    private void iniciarTabCriarProposta()
    {
        try
        {
            organizacaoChoice.getItems().addAll(propostaController.findAllOrganizacao());
            edicaoChoice.getItems().addAll(propostaController.findAllEdicao());
            createPropostaButton.setDisable(false);
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+e.getStatus(), e.getTitle(), e.getDetail());
            createPropostaButton.setDisable(true);
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
            createPropostaButton.setDisable(true);
        }
    }
}
