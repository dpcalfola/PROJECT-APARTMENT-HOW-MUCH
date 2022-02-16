package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {


    @FXML
    private void handleLoginViewLoginButtonAction(ActionEvent event) {
        System.out.println("login-subView login button clicked");

    }

    @FXML
    private void handleLoginViewSignUpButtonAction(ActionEvent event) {
        System.out.println("login-subView sign up button clicked");
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        System.out.println("Login-subView Go To Table clicked");
        PrimaryModel p1 = new PrimaryModel();
        p1.changeBorderPaneCenter("table-subView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
