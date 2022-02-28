package application;

import databaseClass.userModel.UserLoginModelDAO;
import databaseClass.userModel.UserModelVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController extends PrimaryModel implements Initializable {

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
        UserLoginModelDAO dao = new UserLoginModelDAO();
        UserModelVO userInfo = dao.loginUser(getID, getPW);


        // find userVO in database -> userInfo.isCorrectUserInfo == true
        if (userInfo.isCorrectUserInfo() && !Objects.equals(getID, "") && !Objects.equals(getPW, "")) {

            System.out.println("Logged in Successfully");
            setGreetingTextField(getID);
            setStatusDisplayText(getID + " Logged in");

            loginConsequenceTextLgSbView.setText("Hello " + getID + " !! You logged in Successfully");
            loginConsequenceTextLgSbView.setVisible(true);

            // setting after logged-in status

            setButtonsAfterLogin(); // Change button status
            setLoggedInUserKey(userInfo.getUserPrimaryKey()); // set static int LoggedInUserKey


            // test code
            System.out.println("logged in user key : " + userInfo.getUserPrimaryKey() + "\n");

        } else if (!Objects.equals(getID, "") && !Objects.equals(getPW, "")) {
            // this code doesn't execute getID or getPW is empty
            System.out.println("There is no matched user information");
            loginConsequenceTextLgSbView.setText("Couldn't find your information");
            loginConsequenceTextLgSbView.setVisible(true);

        }


    }

    @FXML
    private void handleSignUpButtonLgSbViewAction(ActionEvent event) throws IOException {
        System.out.println("login-subView sign up button clicked");
        changeBorderPaneCenter("signup-subView.fxml");
        
    }

    @FXML
    private void goToSearch(ActionEvent event) throws IOException {
        System.out.println("Login-subView Go To Table clicked");
        changeBorderPaneCenter("table-subView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginConsequenceTextLgSbView.setVisible(false);

    }
}
