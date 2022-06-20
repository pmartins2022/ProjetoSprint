package com.example.javafx.ui.docente;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.controller.docente.DocenteController;
import com.example.javafx.controller.docente.ProjetoController;
import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.LoginContext;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DocenteMainWindowViewController
{


    public Button createPropostaButton;
    public TextField userIdText;
    public TextField tituloText;
    public TextField problemaText;
    public TextField objetivoText;
    public ChoiceBox organizacaoChoice;
    public ChoiceBox edicaoChoice;
    public Button aceitarConviteButton;
    public Button rejeitarConviteButton;
    public ChoiceBox conviteChoice;
    public Button aceitarProposta;
    public Button rejeitarPropostaButton;
    public ChoiceBox<PropostaDTO> propostaChoice;
    public Button aceitarCandidatura;
    public Button rejeitarCandidaturaButton;
    public ChoiceBox<PropostaDTO> candidaturaChoice;
    public Button confirmarECriarPropostaID;
    public TextField idMomentoAvaliacaoText;
    public TextField idArguenteText;
    public TextField idProjetoText;
    public TextField idConteudoText;
    public TextField idOrientadorText;
    public TextField idPresidenteText;
    public Button aceitarConteudoButton;
    public Button rejeitarConteudoButton;
    public ChoiceBox<String> conteudoChoice;
    public TextArea docenteDTOText;
    public TabPane docentePaneID;
    public TabPane rucPaneID;
    public TabPane orientadorPaneID;
    public TabPane mainPaneID;
    public TextField alunoIDTxt;
    public Button confirmarECriarAvaliacao;

    private DocenteController docenteController  ;
    private PropostaController propostaController;
    private ProjetoController projetoController  ;

    @FXML
    void criarProposta(ActionEvent event)
    {

    }

    @FXML
    void definirJuri(ActionEvent event)
    {

    }

    @FXML
    void gerirCandidatura(ActionEvent event)
    {

    }

    @FXML
    void gerirOrientacao(ActionEvent event)
    {

    }

    @FXML
    void gerirProposta(ActionEvent event)
    {

    }

    @FXML
    void gerirSubmissao(ActionEvent event)
    {

    }

    @FXML
    void logOut(ActionEvent event)
    {

    }

    public void setController(DocenteController docenteController, PropostaController propostaController, ProjetoController projetoController)
    {
        this.docenteController = docenteController;
        this.propostaController = propostaController;
        this.projetoController = projetoController;

        mainPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> mainTabPaneChanged(t1));
        docentePaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> docenteTabPaneChanged(t1));
        rucPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> rucTabPaneChanged(t1));
        orientadorPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> orientadorTabPaneChanged(t1));

        iniciarTabHome();
    }

    private void orientadorTabPaneChanged(Number t1)
    {
    }

    public void createProposta(ActionEvent actionEvent)
    {
    }

    public void validateNumberField(KeyEvent keyEvent)
    {
    }

    public void aceitarConvite(ActionEvent actionEvent)
    {
    }

    public void rejeitarConvite(ActionEvent actionEvent)
    {
    }

    public void iniciarCriarProposta()
    {
    }


    private void iniciarGerirCandidaturaProposta()
    {
        try
        {
            List<PropostaDTO> list = propostaController.findAllPropostaCandidatura();
            candidaturaChoice.getItems().addAll(list);
            candidaturaChoice.getSelectionModel().selectFirst();
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void aceitarCandidatura(ActionEvent actionEvent)
    {
        try
        {
            propostaController.acceptCandidaturaProposta(candidaturaChoice.getSelectionModel().getSelectedItem());

        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void rejeitarCandidatura(ActionEvent actionEvent)
    {
        try
        {
            propostaController.rejectCandidaturaProposta(candidaturaChoice.getSelectionModel().getSelectedItem());
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }

    }

    public void iniciarGerirProposta()
    {
        try
        {
            List<PropostaDTO> list = propostaController.findAllPropostaAprovado();
            propostaChoice.getItems().addAll(list);
            propostaChoice.getSelectionModel().selectFirst();
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void aceitarProposta(ActionEvent actionEvent)
    {
        try
        {
            PropostaDTO selected = propostaChoice.getSelectionModel().getSelectedItem();
            propostaController.acceptProposta(propostaChoice.getSelectionModel().getSelectedItem().getId(), alunoIDTxt.getText());
            propostaChoice.getItems().remove(selected);

        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void rejeitarProposta(ActionEvent actionEvent)
    {
        try
        {
            PropostaDTO selected = propostaChoice.getSelectionModel().getSelectedItem();
            propostaController.rejectProposta(propostaChoice.getSelectionModel().getSelectedItem().getId(), alunoIDTxt.getText());
            propostaChoice.getItems().remove(selected);

        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    private void iniciarDefinirJuriAvaliacao()
    {
        System.out.println("Definir júri");
    }

    public void confirmarECriarAvaliacao(ActionEvent actionEvent)
    {
        AvaliacaoDTO avaliacaoDTO = projetoController.createAvaliacao(idMomentoAvaliacaoText.getText(), idOrientadorText.getText(),
                idPresidenteText.getText(), idArguenteText.getText(),
                idProjetoText.getText(), idConteudoText.getText());

        AlertBuilder.showAlert(Alert.AlertType.ERROR, "Definir Júri", "Avaliação Criada", avaliacaoDTO.toString());
    }

    public void aceitarConteudo(ActionEvent actionEvent)
    {
    }


    public void rejeitarConteudo(ActionEvent actionEvent)
    {
    }

    public void mainTabPaneChanged(Number t1)
    {
        System.out.println("Tab pane selecionado: "+t1);

        switch (t1.intValue())
        {
            case 0 -> iniciarTabHome();
            case 1 ->
            {
                docentePaneID.getSelectionModel().select(0);
                iniciarCriarProposta();
            }
            case 2 ->
            {
                rucPaneID.getSelectionModel().select(0);
                iniciarGerirProposta();
            }//case 3 -> iniciarTabCriarUnidadeCurricular();
            //case 4 -> iniciarTabConsultarUC();
        }
    }

    public void docenteTabPaneChanged(Number t1)
    {

        switch (t1.intValue())
        {
            case 0 -> iniciarCriarProposta();
            //case 1 -> iniciarTabCriarEdicaoUC();
            //case 2 -> iniciarTabCriarAnoLetivo();
            //case 3 -> iniciarTabCriarUnidadeCurricular();
            //case 4 -> iniciarTabConsultarUC();
        }

    }

    public void rucTabPaneChanged(Number t1)
    {
        switch (t1.intValue())
        {
            case 0 -> iniciarGerirProposta();
            case 1 -> iniciarGerirCandidaturaProposta();
            case 2 -> iniciarDefinirJuriAvaliacao();
        }
    }


    private void iniciarTabHome()
    {
        docenteDTOText.setText(LoginContext.getCurrentUser().toString());
    }


}
