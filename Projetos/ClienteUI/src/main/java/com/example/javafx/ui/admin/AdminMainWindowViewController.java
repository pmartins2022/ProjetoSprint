package com.example.javafx.ui.admin;

import com.example.javafx.controller.admin.AdminController;
import com.example.javafx.controller.admin.AnoLetivoController;
import com.example.javafx.controller.admin.EdicaoUCController;
import com.example.javafx.controller.admin.UnidadeCurricularController;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.dto.EdicaoUCDTO;
import com.example.javafx.dto.UnidadeCurricularDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.LoginContext;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.util.Locale;

@Controller
public class AdminMainWindowViewController
{
    public ChoiceBox<String> txtChoiceBoxUC;
    public ChoiceBox<String> txtChoiceBoxAnoLetivo;
    public TextField siglaAnoLetivo;

    public ChoiceBox<String> choiceBoxUC;
    public TextArea textUC;
    public TabPane mainPaneID;
    public TextArea textHomeTxt;
    public TextField siglaUCText;
    public TextField denominacaoUCText;
    public TextField rucIdText;
    private AdminController adminController;

    private EdicaoUCController edicaoUCController;

    private UnidadeCurricularController unidadeCurricularController;

    private AnoLetivoController anoLetivoController;

    @FXML
    void consultarUC(ActionEvent event)
    {

    }

    @FXML
    void criarAnoLetivo(ActionEvent event)
    {

    }

    @FXML
    void criarEdicaoUC(ActionEvent event)
    {

    }

    @FXML
    void criarUC(ActionEvent event)
    {

    }

    @FXML
    void logOut(ActionEvent event)
    {

    }

    public void setController(AdminController adminController, EdicaoUCController edicaoUCController, AnoLetivoController anoLetivoController,
                              UnidadeCurricularController unidadeCurricularController)
    {
        this.adminController = adminController;
        this.edicaoUCController = edicaoUCController;
        this.anoLetivoController = anoLetivoController;
        this.unidadeCurricularController = unidadeCurricularController;

        mainPaneID.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> tabPaneChanged(t1));

        iniciarTabHome();
    }

    public void validateNumberField(ActionEvent actionEvent)
    {
    }

    public void createEdicaoUC(ActionEvent actionEvent)
    {
        try
        {
            EdicaoUCDTO edicaoUCDTO = edicaoUCController.createEdicaoUC(txtChoiceBoxUC.getSelectionModel().getSelectedIndex(),
                    txtChoiceBoxAnoLetivo.getSelectionModel().getSelectedIndex(),Long.parseLong(rucIdText.getText()));
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Edição de Unidade Curricular criada com sucesso. " + edicaoUCDTO.toString());
        } catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }
    }

    public void closeWindow(ActionEvent actionEvent)
    {
    }

    public void createAnoLetivo(ActionEvent actionEvent)
    {
        if (siglaAnoLetivo.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Sigla");
        }
        try
        {
            final AnoLetivoDTO anoLetivo = anoLetivoController.createAnoLetivo(new AnoLetivoDTO(siglaAnoLetivo.getText()));
            siglaAnoLetivo.clear();
            AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Sucesso", "Ano Letivo criado com sucesso. " + anoLetivo.toString());
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        }
        catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro geral", "Erro geral", e.getMessage());
        }

    }

    public void createUnidadeCurricular(ActionEvent actionEvent)
    {
        if (siglaUCText.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Sigla");
            return;
        }

        if (denominacaoUCText.getText().isEmpty())
        {
            AlertBuilder.showAlert(Alert.AlertType.WARNING,"Atenção","Atenção", "Preencha o campo Denominação");
            return;
        }

        try
        {
            final UnidadeCurricularDTO unidadeCurricular = unidadeCurricularController.createUnidadeCurricular
                    (new UnidadeCurricularDTO(siglaUCText.getText(), denominacaoUCText.getText()));
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

    public void tabPaneChanged(Number t1)
    {
        System.out.println("Tab pane selecionado: "+t1);

        switch (t1.intValue())
        {
            case 0 -> iniciarTabHome();
            case 1 -> iniciarTabCriarEdicaoUC();
            case 2 -> iniciarTabCriarAnoLetivo();
            case 3 -> iniciarTabCriarUnidadeCurricular();
            case 4 -> iniciarTabConsultarUC();
        }
    }

    private void iniciarTabConsultarUC()
    {
        choiceBoxUC.getItems().clear();
        textUC.setEditable(false);

        try
        {
            choiceBoxUC.getItems().addAll(unidadeCurricularController.findAllUnidadeCurricular());
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
                    UnidadeCurricularDTO var = unidadeCurricularController.getFromLista(choiceBoxUC.getSelectionModel().getSelectedIndex());
                    if (var == null){
                        textUC.clear();
                    }
                    else{
                        textUC.setText(var.toStringText());
                    }

                }
        );
    }

    private void iniciarTabCriarUnidadeCurricular()
    {
    }

    private void iniciarTabCriarAnoLetivo()
    {

    }

    private void iniciarTabCriarEdicaoUC()
    {
        txtChoiceBoxUC.getItems().clear();
        txtChoiceBoxAnoLetivo.getItems().clear();
        try
        {
            txtChoiceBoxUC.getItems().addAll(edicaoUCController.findAllUC());
            txtChoiceBoxAnoLetivo.getItems().addAll(edicaoUCController.findAllAnoLetivo());

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

    private void iniciarTabHome()
    {
        textHomeTxt.setText(LoginContext.getCurrentUser().toString());
    }
}