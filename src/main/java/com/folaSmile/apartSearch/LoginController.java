package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseClass.userModel.UserLoginModelDAO;
import com.folaSmile.apartSearch.databaseClass.userModel.UserModelVO;
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

        String getID = idLgSbTextField.getText();
        String getPW = pwLgSbPasswordField.getText();


        // try login
        UserLoginModelDAO dao = new UserLoginModelDAO();
        UserModelVO userInfo = dao.loginUser(getID, getPW);


        // find userVO in database -> userInfo.isCorrectUserInfo == true
        if (userInfo.isCorrectUserInfo() && !Objects.equals(getID, "") && !Objects.equals(getPW, "")) {


            // set message on top text
            setGreetingTextField(getID + " 님 반갑습니다 !!");
            setStatusDisplayText(getID + " Logged in");

            loginConsequenceTextLgSbView.setText(getID + "님의 계정에 로그인 되었습니다.");
            loginConsequenceTextLgSbView.setVisible(true);

            //
            // setting after logged-in status
            // Change button status
            setButtonsAfterLogin();

            // set static user field
            setLoggedInUserKey(userInfo.getUserPrimaryKey());
            setLoggedInUserID(userInfo.getUserID());


        } else if (!Objects.equals(getID, "") && !Objects.equals(getPW, "")) {
            // this code doesn't execute getID or getPW is empty
            loginConsequenceTextLgSbView.setText("계정 정보를 찾을 수 없습니다.");
            loginConsequenceTextLgSbView.setVisible(true);

        }


    }

    @FXML
    private void handleSignUpButtonLgSbViewAction(ActionEvent event) throws IOException {
        System.out.println("login-subView sign up button clicked");
        changeBorderPaneCenter("signup-subView.fxml");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginConsequenceTextLgSbView.setVisible(false);

    }
}
