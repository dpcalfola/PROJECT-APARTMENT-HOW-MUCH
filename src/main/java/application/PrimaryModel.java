package application;

import databaseClass.table.ConstraintModelVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

import static application.Application.mainControllerHandle;

public class PrimaryModel {

    static ConstraintModelVO primaryModelConstraintModelVO;

    void changeBorderPaneCenter(String fxmlFile) throws IOException {
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        mainControllerHandle.getMainPane().setCenter(view);
    }

    void changeBorderPaneCenterSearch(String fxmlFile, ConstraintModelVO ConstraintModelVo) throws IOException {
        primaryModelConstraintModelVO = ConstraintModelVo;
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));


        String testCode2 = primaryModelConstraintModelVO.getConstraintKeyword();
        System.out.println("testCode2: " + testCode2);


        mainControllerHandle.getMainPane().setCenter(view);
    }

    void changeGreetingTextField(String text) {
        mainControllerHandle.getGreetingTextField().setText("Hello " + text + " !!");
    }

    void changeStatusDisplayText(String text) {
        mainControllerHandle.getStatusDisplayText().setText(text);
    }

    @FXML
    TextField getSearchTextField() {
        return mainControllerHandle.getSearchTextField();
    }

    public ConstraintModelVO getPrimaryModelConstraintModelVO() {
        return primaryModelConstraintModelVO;
    }
}





















