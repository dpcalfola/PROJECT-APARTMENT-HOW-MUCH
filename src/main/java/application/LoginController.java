package application;

import databaseClass.user.UserLoginDAO;
import databaseClass.user.UserVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    TextField idLgSbTextField;
    @FXML
    PasswordField pwLgSbPasswordField;
    @FXML
    Text loginConsequenceTextLgSbView;



    // *LgSbView == Login-subView

    @FXML
    private void handleLoginButtonLgSbViewAction(ActionEvent event) throws IOException {
        System.out.println("login-subView login button clicked");
        String getID = idLgSbTextField.getText();
        String getPW = pwLgSbPasswordField.getText();

        System.out.println("ID: " + getID + "\nPW: " + getPW);

        // try login
        UserLoginDAO dao = new UserLoginDAO();
        UserVO userInfo = dao.loginUser(getID, getPW);


        // find userVO in database -> userInfo.isCorrectUserInfo == true
        if (userInfo.isCorrectUserInfo() && !Objects.equals(getID, "") && !Objects.equals(getPW, "")) {
            System.out.println("Logged in Successfully");

            PrimaryModel p1 = new PrimaryModel();
            p1.changeGreetingTextField(getID);
            p1.changeStatusDisplayText(getID + " Logged in");

            loginConsequenceTextLgSbView.setText("Hello " + getID + " !! You logged in Successfully");
            loginConsequenceTextLgSbView.setVisible(true);

        } else if (!Objects.equals(getID, "") && !Objects.equals(getPW, "")) {
            // this code doesn't execute getID or getPW is empty
            System.out.println("There is no matched user information");
            loginConsequenceTextLgSbView.setText("Couldn't find your information");
            loginConsequenceTextLgSbView.setVisible(true);

        }


    }

    @FXML
    private void handleSignUpButtonLgSbViewAction(ActionEvent event) {
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
        loginConsequenceTextLgSbView.setVisible(false);

    }
}
