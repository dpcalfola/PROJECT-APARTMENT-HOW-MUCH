package application;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Text statusDisplayText;

    @FXML
    private BorderPane mainPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
        System.out.println("Search Button clicked !!");
        statusDisplayText.setText("Search Button clicked");
        Pane view = FXMLLoader.load(getClass().getResource("search-view.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        System.out.println("Login Button clicked !!");
        statusDisplayText.setText("Login Button clicked");
        Pane view = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        mainPane.setCenter(view);

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
    }


    // login view button

    @FXML
    private void handleLoginViewLoginButtonAction(ActionEvent event) throws IOException {
        System.out.println("Login view - Login View Button Clicked");

    }

    @FXML
    private void handleLoginViewSignUpButtonAction(ActionEvent event) throws IOException {
        System.out.println("Login view - Sign View Button Clicked");



    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        System.out.println("goToSearch clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();
        MainController mController = loader.getController();
        Pane view = FXMLLoader.load(getClass().getResource("search-view.fxml"));
        mController.statusDisplayText.setText("12345");
        mController.mainPane.setCenter(view);
    }


}
