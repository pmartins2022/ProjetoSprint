package com.example.javafx.ui;

import com.example.javafx.dto.UtilizadorAuthDTO;
import com.example.javafx.exception.ErrorDetail;
import com.example.javafx.model.LoginContext;
import com.example.javafx.repository.rest.UtilizadorRestRepository;
import com.example.javafx.ui.utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginViewController
{
    public TextField username;
    public PasswordField password;
    public Button login;
    public Label labelErro;
    public Button close;

    @Autowired
    private UtilizadorRestRepository utilizadorRestRepository;

    public void closeWindow(ActionEvent actionEvent)
    {
        username.getScene().getWindow().fireEvent(
                new WindowEvent(username.getScene().getWindow(), javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void login(ActionEvent actionEvent)
    {
        //fazer login
        System.out.println("Login: "+username.getText()+" , "+password.getText());
        try
        {
            UtilizadorAuthDTO username1 = utilizadorRestRepository.findByUsername(username.getText());

            UtilizadorAuthDTO user = new UtilizadorAuthDTO(username1.getId(),username1.getUsername(), password.getText(), username1.getTipoUtilizador());
            LoginContext.setUser(user);

            AlertBuilder.showAlert(Alert.AlertType.INFORMATION,"!!!","DEU","FEZ LOGIN "+user);
        }
        catch (ErrorDetail e)
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
}
