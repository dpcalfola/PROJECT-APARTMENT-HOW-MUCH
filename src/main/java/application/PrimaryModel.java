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


    // Static field
    static ConstraintModelVO staticConstraintModelVO;

    // loggedInUserKey has some value always
    // -1 : guest
    // ect : userPrimaryKey from MySQL
    static int loggedInUserKey;

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
    // Draw data table on center pane
    static void drawDataTableOnCenter(ConstraintModelVO constraintModelVO) throws IOException {


        // Set static property
        staticConstraintModelVO = constraintModelVO;

        // draw center data table
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(PrimaryModel.class.getResource("table-subView.fxml")));
        mainControllerHandle.getMainPane().setCenter(view);

        // After center pane is loaded by this method,
        // TableController will call TableModelDAO

    }


    // TableController use this method
    @FXML
    TextField getSearchTextField() {
        return mainControllerHandle.getSearchTextField();
    }

    // TableController use this method
    public ConstraintModelVO getStaticModelConstraintModelVO() {
        return staticConstraintModelVO;
    }


    // LoggedInUserKey getter and setter
    public static int getLoggedInUserKey() {
        return loggedInUserKey;
    }

    public static void setLoggedInUserKey(int loggedInUserKey) {
        PrimaryModel.loggedInUserKey = loggedInUserKey;
    }


}





















