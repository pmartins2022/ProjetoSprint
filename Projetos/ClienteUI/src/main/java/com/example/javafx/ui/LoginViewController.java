package com.example.javafx.ui;

import com.example.javafx.controller.PropostaController;
import com.example.javafx.controller.admin.AdminController;
import com.example.javafx.controller.admin.AnoLetivoController;
import com.example.javafx.controller.admin.EdicaoUCController;
import com.example.javafx.controller.admin.UnidadeCurricularController;
import com.example.javafx.controller.aluno.AlunoController;
import com.example.javafx.controller.docente.AvaliacaoController;
import com.example.javafx.controller.docente.DocenteController;
import com.example.javafx.controller.docente.ProjetoController;
import com.example.javafx.dto.UtilizadorAuthDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.LoginContext;
import com.example.javafx.repository.rest.UtilizadorRestRepository;
import com.example.javafx.ui.admin.AdminMainWindowViewController;
import com.example.javafx.ui.aluno.AlunoMainWindowViewController;
import com.example.javafx.ui.docente.DocenteMainWindowViewController;
import com.example.javafx.ui.utils.AlertBuilder;
import com.example.javafx.ui.utils.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LoginViewController
{
    public TextField username;
    public PasswordField password;
    public Button login;
    public Label labelErro;
    public Button close;

    //Controllers
    @Autowired
    private AdminController adminController;

    @Autowired
    private DocenteController docenteController;

    @Autowired
    private AlunoController alunoController;

    @Autowired
    private EdicaoUCController edicaoUCController;

    @Autowired
    private AnoLetivoController anoLetivoController;

    @Autowired
    private UnidadeCurricularController unidadeCurricularController;

    @Autowired
    private ProjetoController projetoController;

    @Autowired
    private PropostaController propostaController;

    @Autowired
    private AvaliacaoController avaliacaoController;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;





    public void closeWindow(ActionEvent actionEvent)
    {
        username.getScene().getWindow().fireEvent(
                new WindowEvent(username.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void login(ActionEvent actionEvent)
    {
        JavaFXUtils.setJanelaLogin(this);

        //fazer login

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        try
        {
            UtilizadorAuthDTO username1 = utilizadorRestRepository.findByUsername(username.getText());

            System.out.println("Login: "+username1.getUsername()+" , "+username1.getPassword());

            if (!encoder.matches(password.getText(), username1.getPassword()))
            {
                AlertBuilder.showAlert(Alert.AlertType.ERROR,"ERRRRRRO!","NADA DISSO","CREDENCIAIS ERRADAS");
                return;
            }

            UtilizadorAuthDTO user = new UtilizadorAuthDTO(username1.getId(), username1.getUsername(), username1.getPassword(), username1.getTipoUtilizador());
            LoginContext.setUser(user, password.getText());

            switch (user.getTipoUtilizador())
            {
                case "ROLE_ADMIN" -> abrirJanelaAdmin();
                case "ROLE_DOCENTE" -> abrirJanelaDocente(user);
                case "ROLE_ALUNO" -> abrirJanelaAluno();
            }
        }
        catch (ErrorDetail e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro " + e.getStatus(), e.getTitle(), e.getDetail());
        } catch (Exception e)
        {
            AlertBuilder.showAlert(Alert.AlertType.ERROR, "Erro fatal", "Erro fatal", e.getMessage());
        }
    }

    private void abrirJanelaDocente(UtilizadorAuthDTO user) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("BEM VINDO DOCENTE: "+user.getUsername());
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DOCENTE/DocenteMainWindow.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        fxmlLoader.<DocenteMainWindowViewController>getController().setController(docenteController, propostaController, projetoController, avaliacaoController);

        stage.setResizable(false);

        stage.show();

        closeWindow(null);
    }

    private void abrirJanelaAdmin() throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("BEM VINDO ADMIN");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ADMIN/AdminMainWindow.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        fxmlLoader.<AdminMainWindowViewController>getController().setController(adminController, edicaoUCController, anoLetivoController, unidadeCurricularController);

        stage.setResizable(false);

        stage.show();

        closeWindow(null);
    }

    private void abrirJanelaAluno() throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("BEM VINDO ALUNO");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ALUNO/AlunoMainWindow.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        fxmlLoader.<AlunoMainWindowViewController>getController().setController(alunoController, propostaController);

        stage.setResizable(false);

        stage.show();

        closeWindow(null);
    }

}
