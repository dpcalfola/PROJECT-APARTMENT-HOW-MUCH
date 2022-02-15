package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("login-view login button clicked");

    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        System.out.println("login-view sign up button clicked");
    }

    @FXML
    private void goToSearch(ActionEvent event){
        System.out.println("goToSearch clicked");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
