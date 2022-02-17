package application;

import databaseClass.testConnection.TestConnection;
import databaseClass.user.UserLoginConfirmTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    //Field
    @FXML
    private Text greetingTextField;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Text statusDisplayText;

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // DB connection test
//        TestConnection t1 = new TestConnection();
//        t1.testConnect();

        // login confirm test
        UserLoginConfirmTest c1 = new UserLoginConfirmTest();
        c1.confirmUser();

    }


    //Button
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {
        System.out.println("Table button clicked !!");
        statusDisplayText.setText("Table Button clicked");
        PrimaryModel p1 = new PrimaryModel();
        p1.changeBorderPaneCenter("table-subView.fxml");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        System.out.println("Login Button clicked !!");
        statusDisplayText.setText("Login Button clicked");
        PrimaryModel p1 = new PrimaryModel();
        p1.changeBorderPaneCenter("login-subView.fxml");
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        System.out.println("Logout Button clicked !!");
        statusDisplayText.setText("Logout Button clicked");
    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException {
        System.out.println("SignUp Button clicked !!");
        statusDisplayText.setText("SignUp Button clicked");
        PrimaryModel p1 = new PrimaryModel();
        p1.changeBorderPaneCenter("signup-subView.fxml");
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
        System.out.println("Search Button clicked !!");
        statusDisplayText.setText("Search Button clicked");
    }


    // getter
    public BorderPane getMainPane() {
        return mainPane;
    }

    public Text getGreetingTextField() {
        return greetingTextField;
    }

    public Text getStatusDisplayText() {
        return statusDisplayText;
    }
}
