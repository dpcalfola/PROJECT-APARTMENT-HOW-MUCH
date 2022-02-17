package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

import static application.Application.mainControllerHandle;

public class PrimaryModel {

    void changeBorderPaneCenter(String fxmlFile) throws IOException {
        Pane view;
        view = FXMLLoader.load(getClass().getResource(fxmlFile));
        mainControllerHandle.getMainPane().setCenter(view);
    }

    void changeGreetingTextField(String text) throws IOException{
        mainControllerHandle.getGreetingTextField().setText("Hello " + text + " !!");
    }

    void changeStatusDisplayText(String text){
        mainControllerHandle.getStatusDisplayText().setText(text);
    }

}





















