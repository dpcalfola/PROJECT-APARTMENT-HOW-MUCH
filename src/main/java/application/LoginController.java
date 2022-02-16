package application;

import databaseClass.user.UserLoginDAO;
import databaseClass.user.UserVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    TextField idLgSbTextField ;
    @FXML
    PasswordField pwLgSbPasswordField ;



    // LgSbView = Login-subView

    @FXML
    private void handleLoginButtonLgSbViewAction(ActionEvent event) {
        System.out.println("login-subView login button clicked");
        String getID = idLgSbTextField.getText();
        String getPW = pwLgSbPasswordField.getText();

        System.out.println("ID: " + getID + "\nPW: " + getPW);

        // 로그인 시도
        UserLoginDAO dao = new UserLoginDAO();
        UserVO userInfo = dao.loginUser(getID, getPW);
        // 로그인 성공 or 실패
        if(userInfo.isCorrectUserInfo()){
            System.out.println("Logged in Successfully");
        }else{
            System.out.println("There is no matched user information");
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

    }
}
