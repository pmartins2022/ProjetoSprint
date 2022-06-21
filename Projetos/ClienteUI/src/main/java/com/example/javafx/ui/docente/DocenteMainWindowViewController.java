package com.example.javafx.ui.docente;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.controller.docente.DocenteController;
import com.example.javafx.controller.docente.ProjetoController;
import com.example.javafx.dto.AvaliacaoDTO;
import com.example.javafx.dto.ConteudoDTO;
import com.example.javafx.dto.ConviteDTO;
import com.example.javafx.dto.PropostaDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.LoginContext;
import com.example.javafx.ui.utils.AlertBuilder;
import com.example.javafx.ui.utils.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;
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
    public ChoiceBox<String> organizacaoChoice;
    public ChoiceBox<String> edicaoChoice;
    public Button aceitarConviteButton;
    public Button rejeitarConviteButton;
    public ChoiceBox<String> conviteChoice;
    public Button aceitarProposta;
    public Button rejeitarPropostaButton;
    public ChoiceBox<PropostaDTO> propostaChoice;
    public Button aceitarCandidatura;
    public Button rejeitarCandidaturaButton;
    public ChoiceBox<PropostaDTO> candidaturaChoice;
    public TextField idMomentoAvaliacaoText;
    public TextField idArguenteText;
    public TextField idProjetoText;
    public TextField idConteudoText;
    public TextField idOrientadorText;
    public TextField idPresidenteText;
    public Button aceitarConteudoButton;
    public Button rejeitarConteudoButton;
    public ChoiceBox<ConteudoDTO> conteudoChoice;
    public TextArea docenteDTOText;
    public TabPane docentePaneID;
    public TabPane rucPaneID;
    public TabPane orientadorPaneID;
    public TabPane mainPaneID;
    public TextField alunoIDTxt;
    public Button confirmarECriarAvaliacao;

    private DocenteController docenteController;
    private PropostaController propostaController;
    private ProjetoController projetoController;


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
    public void logOut(ActionEvent event)
    {
        JavaFXUtils.aparecerJanelaLogin();
        closeWindow(null);
    }

    public void setController(DocenteController docenteController, PropostaController propostaController, ProjetoController projetoController)
    {
        this.docenteController = docenteController;
        this.propostaController = propostaController;
        this.projetoController = projetoController;

        mainPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> mainTabPaneChanged(t1));
        docentePaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> docenteTabPaneChanged(t1));
        rucPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> rucTabPaneChanged(t1));

        iniciarTabHome();
    }


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

    public void aceitarConvite(ActionEvent actionEvent)
    {
        try
        {
            ConviteDTO dto = propostaController.acceptConvite(conviteChoice.getSelectionModel().getSelectedIndex());
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Sucesso!", "Alteracao realizada com sucesso.", dto.toString());
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

    public void rejeitarConvite(ActionEvent actionEvent)
    {
        try
        {
            ConviteDTO dto = propostaController.rejectConvite(conviteChoice.getSelectionModel().getSelectedIndex());
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Sucesso!", "Alteracao realizada com sucesso.", dto.toString());
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

    public void iniciarCriarProposta()
    {
        try
        {
            userIdText.setText(LoginContext.getCurrentUser().getId().toString());

            organizacaoChoice.getItems().clear();
            edicaoChoice.getItems().clear();

            organizacaoChoice.getItems().addAll(propostaController.findAllOrganizacao());
            edicaoChoice.getItems().addAll(propostaController.findAllEdicao());
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

    public void iniciarGerirSubmissao()
    {
        try
        {
            List<ConteudoDTO> list = projetoController.findAllConteudoOfOrientador(LoginContext.getCurrentUser().getId());
            conteudoChoice.getItems().addAll(list);
            conteudoChoice.getSelectionModel().selectFirst();
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void aceitarConteudo()
    {
        try
        {
            ConteudoDTO selected = conteudoChoice.getSelectionModel().getSelectedItem();
            projetoController.acceptConteudo(conteudoChoice.getSelectionModel().getSelectedItem().getId());
            conteudoChoice.getItems().remove(selected);

        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void rejeitarConteudo()
    {
        try
        {
            ConteudoDTO selected = conteudoChoice.getSelectionModel().getSelectedItem();
            projetoController.rejectConteudo(conteudoChoice.getSelectionModel().getSelectedItem().getId());
            conteudoChoice.getItems().remove(selected);

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

    private void iniciarAceitarRejeitarConvite()
    {
        try
        {
            conviteChoice.getItems().clear();
            conviteChoice.getItems().addAll(propostaController.getConvites());
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
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
            }
            case 3 -> iniciarGerirSubmissao();
        }
    }

    public void docenteTabPaneChanged(Number t1)
    {

        switch (t1.intValue())
        {
            case 0 -> iniciarCriarProposta();
            case 1 -> iniciarAceitarRejeitarConvite();
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

    public void closeWindow(ActionEvent actionEvent)
    {
        userIdText.getScene().getWindow().fireEvent(
                new WindowEvent(userIdText.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
