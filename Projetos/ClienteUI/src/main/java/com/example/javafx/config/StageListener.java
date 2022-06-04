package com.example.javafx.config;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Ponto de entrada da aplicação java fx, gerido pelo Spring.
 */
@Component
public class StageListener implements ApplicationListener<StageReadyEvent>
{
    private final String STAGE_TITLE;
    private final Resource fxmlResource;

    @Autowired
    private final ApplicationContext ac;

    public StageListener(@Value("${spring.application.ui.title}") String stageTitle,
                         @Value("classpath:/fxml/main-window-view.fxml") Resource resource,
                         ApplicationContext ac)
    {
        this.STAGE_TITLE = stageTitle;
        this.fxmlResource = resource;
        this.ac = ac;
    }

    /**
     * Chamado quando a aplicação é iniciada.
     * @param event Evento de início da aplicação.
     */
    @Override
    public void onApplicationEvent(StageReadyEvent event)
    {
        try
        {
            Stage stage = event.getStage();

            URL url = fxmlResource.getURL();
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(ac::getBean);

            Parent root = loader.load();

            Scene scene;

            scene = new Scene(root,800,600);

            stage.setScene(scene);
            stage.setTitle(STAGE_TITLE);
            stage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}