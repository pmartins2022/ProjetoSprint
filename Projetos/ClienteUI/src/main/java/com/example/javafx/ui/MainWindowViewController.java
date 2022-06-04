package com.example.javafx.ui;

import com.example.javafx.controller.*;
import com.example.javafx.dto.AnoLetivoDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Classe controller FXML para a janela principal da aplicacao.
 */
@Controller
public class MainWindowViewController
{
    @FXML
    public MenuItem closeItem;
    @FXML
    public MenuItem aboutItem;
    @FXML
    public MenuItem putTestItem;

    @Autowired
    private AnoLetivoController anoLetivoController;

    @Autowired
    private PropostaController propostaController;

    @Autowired
    private EdicaoUCController edicaoUCController;

    @Autowired
    private UnidadeCurricularController unidadeCurricularController;

    /**
     * Metodo de inicializacao da janela principal.
     */
    @FXML
    public void initialize()
    {
        closeItem.setOnAction(event -> Platform.exit());
        aboutItem.setOnAction(event -> AlertBuilder.showAlert(Alert.AlertType.INFORMATION, "Sobre", "UI Client", "Teste software JavaFX com Spring."));
    }

    /**
     * Criar uma nova janela para criar ano letivo.
     * @param actionEvent informacao do evento.
     * @throws IOException caso ocorra erro ao carregar o ficheiro fxml.
     */
    public void createAnoLetivoWindow(ActionEvent actionEvent) throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("Create Ano Letivo");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createAnoLetivo-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        fxmlLoader.<CreateAnoLetivoViewController>getController().setController(anoLetivoController);

        stage.show();
    }

    /**
     * Criar uma nova janela para criar proposta.
     * @param actionEvent informacao do evento.
     * @throws IOException caso ocorra erro ao carregar o ficheiro fxml.
     */
    public void createPropostaWindow(ActionEvent actionEvent) throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("Create Proposta");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createProposta-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        stage.show();

        fxmlLoader.<CreatePropostaViewController>getController().initialize(propostaController);
    }

    /**
     * Criar uma nova janela para criar edicao UC.
     * @param actionEvent informacao do evento.
     * @throws IOException caso ocorra erro ao carregar o ficheiro fxml.
     */
    public void createEdicaoUCWindow(ActionEvent actionEvent) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Create Edicao UC");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createEdicaoUC-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        stage.show();

        fxmlLoader.<CreateEdicaoUCViewController>getController().setController(edicaoUCController);
    }

    /**
     * Criar uma nova janela para criar unidade curricular.
     * @param actionEvent informacao do evento.
     * @throws IOException caso ocorra erro ao carregar o ficheiro fxml.
     */
    public void createUnidadeCurricularWindow(ActionEvent actionEvent) throws IOException
    {

        Stage stage = new Stage();
        stage.setTitle("Create Unidade Curricular");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/createUnidadeCurricular-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        fxmlLoader.<CreateUnidadeCurricularViewController>getController().setController(unidadeCurricularController);

        stage.show();
    }

    /**
     * Criar uma nova janela para consultar unidades curriculares existentes.
     * @param actionEvent informacao do evento.
     * @throws IOException caso ocorra erro ao carregar o ficheiro fxml.
     */
    public void consultarUnidadeCurricularWindow(ActionEvent actionEvent) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Consultar Unidade Curricular");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/consultarUC-window-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());

        stage.show();

        fxmlLoader.<ConsultarUCViewController>getController().initialize(unidadeCurricularController);
    }

}