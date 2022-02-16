package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

import static application.Application.mainControllerHandle;

public class PrimaryModel {

    public void changeBorderPaneCenter(String fxmlFile) throws IOException {
        Pane view;
        view = FXMLLoader.load(getClass().getResource(fxmlFile));
        mainControllerHandle.getMainPane().setCenter(view);
    }
}





















