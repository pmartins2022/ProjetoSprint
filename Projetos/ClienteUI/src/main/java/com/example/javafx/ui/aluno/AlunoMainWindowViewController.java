package com.example.javafx.ui.aluno;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.controller.aluno.AlunoController;
import com.example.javafx.dto.*;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.EstadoConvite;
import com.example.javafx.model.LoginContext;
import com.example.javafx.ui.utils.AlertBuilder;
import com.example.javafx.ui.utils.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Controller;

/**
 * Classe controller FXML para a janela do aluno.
 */
@Controller
public class AlunoMainWindowViewController
{
    public TextField userIdText;
    public TextField tituloText;
    public TextField problemaText;
    public TextField objetivoText;
    public ChoiceBox<OrganizacaoDTO> organizacaoChoice;
    public ChoiceBox<EdicaoUCDTO> edicaoChoice;
    public TabPane alunoPane;
    public Button logOutButton;
    public ChoiceBox<UtilizadorDTO> docenteChoiceBox;
    public TextArea txtPropostaInfo;
    public ChoiceBox<PropostaDTO> propostaChoiceBox;
    public TextArea alunoDTOText;
    public Button createPropostaButton;
    private AlunoController alunoController;
    private PropostaController propostaController;

    /**
     * Método que faz logout do Utilizador
     * @param event
     */
    @FXML
    public void logOut(ActionEvent event)
    {
        JavaFXUtils.aparecerJanelaLogin();
        closeWindow();
    }

    /**
     * Método que fecha Janela
     */
    @FXML
    private void closeWindow()
    {
        logOutButton.getScene().getWindow().fireEvent(
                new WindowEvent(logOutButton.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    /**
     * Atribui controllers aos atributos da clsse
     * @param alunoController alunoController
     * @param controller propostaController
     */
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
            PropostaDTO dto = propostaController.createProposta(Long.parseLong(userIdText.getText()), organizacaoChoice.getSelectionModel().getSelectedItem().getId(), edicaoChoice.getSelectionModel().getSelectedItem().getId(), tituloText.getText(), problemaText.getText(), objetivoText.getText());
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

    /**
     * Método que cria convite
     * @param actionEvent actionEvent
     */
    public void createConvite(ActionEvent actionEvent)
    {
        try
        {
            ConviteDTO dto = new ConviteDTO(LoginContext.getCurrentUser().getId(),
                    docenteChoiceBox.getSelectionModel().getSelectedItem().getId(), propostaController.getCurrent().getIdProposta(), EstadoConvite.PENDENTE);

            ConviteDTO convite = propostaController.createConvite(dto);
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Convite criada com sucesso. " + convite.toString());
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
     * Método que cria Candidatura
     * @param actionEvent actionEvent
     */
    public void createCandidatura(ActionEvent actionEvent)
    {
        try
        {
            propostaController.createAlunoCandidaturaProposta(propostaChoiceBox.getSelectionModel().getSelectedItem().getId());
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "SUCESSO", "SUCESSO Candidatura", "Aluno Candidatura Proposta");
            iniciarTabAlunoCandidaturaProposta();
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    /**
     * Método que gere Tabs
     * @param t1
     */
    public void tabPaneChanged(Number t1)
    {
        System.out.println("Tab pane selecionado: "+t1);

        switch (t1.intValue())
        {
            case 0 -> iniciarTabHome();
            case 1 -> iniciarTabCriarProposta();
            case 2 -> iniciarTabAlunoCandidaturaProposta();
            case 3 -> iniciarTabCriarConviteOrientacao();
        }
    }

    /**
     * Método que popula choiceBox propostaChoiceBox
     */
    private void iniciarTabAlunoCandidaturaProposta()
    {
        try
        {
            propostaChoiceBox.getItems().clear();

            propostaChoiceBox.getItems().addAll(propostaController.findAllPropostaAprovado());
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
            createPropostaButton.setDisable(true);
            propostaChoiceBox.getItems().clear();
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
            createPropostaButton.setDisable(true);
            propostaChoiceBox.getItems().clear();
        }
    }

    /**
     * Método que popula choiceBoxs docenteChoiceBox e campo txtPropostaInfo
     */
    private void iniciarTabCriarConviteOrientacao()
    {
        try
        {
            PropostaCandidaturaDTO dto = propostaController.findByEstadoAndAlunoid();
            txtPropostaInfo.setText(dto.toString());
            docenteChoiceBox.getItems().clear();
            docenteChoiceBox.getItems().addAll(propostaController.findAllDocente());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro "+e.getStatus(), e.getTitle(), e.getDetail());
            docenteChoiceBox.getItems().clear();
            createPropostaButton.setDisable(true);
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
            docenteChoiceBox.getItems().clear();
            createPropostaButton.setDisable(true);
        }
    }

    /**
     * Método que inicia Tab Home
     */
    private void iniciarTabHome()
    {
        alunoDTOText.setText(alunoController.getDTOInfo());
    }

    /**
     * Métodoq que popula choiceBoxs organizacaoChoice e edicaoChoice
     */
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
