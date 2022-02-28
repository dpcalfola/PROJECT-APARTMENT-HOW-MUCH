package application;

import databaseClass.tableModel.ConstraintModelVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

import static application.Application.mainControllerHandle;

public class PrimaryModel {

    static ConstraintModelVO primaryModelConstraintModelVO;


    // Method which changes center pane
    void changeBorderPaneCenter(String fxmlFile) throws IOException {
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        mainControllerHandle.getMainPane().setCenter(view);
    }

    // set main view field property
    void setGreetingTextField(String text) {
        mainControllerHandle.getGreetingTextField().setText("Hello " + text + " !!");
    }

    void setStatusDisplayText(String text) {
        mainControllerHandle.getStatusDisplayText().setText(text);
    }


    // static void
    // get constraint values -> setting static field "primaryModelConstraintModelVO" and...
    // 제약 조건의 데이터를 읽어서 static field 인 primaryModelConstraintModelVO 에 셋팅한다. 그리고
    // Change center pane
    static void changeBorderPaneCenterDataTable(String fxmlFile, ConstraintModelVO constraintModelVO) throws IOException {

        // put into static VO
        primaryModelConstraintModelVO = constraintModelVO;

        // draw center
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(PrimaryModel.class.getResource(fxmlFile)));
        mainControllerHandle.getMainPane().setCenter(view);

    }


    // TableController use this method
    @FXML
    TextField getSearchTextField() {
        return mainControllerHandle.getSearchTextField();
    }


    // TableController use this method
    public ConstraintModelVO getPrimaryModelConstraintModelVO() {
        return primaryModelConstraintModelVO;
    }


}





















