package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseClass.userModel.UserLoginModelDAO;
import com.folaSmile.apartSearch.databaseClass.userModel.UserSignUpModelDAO;
import com.folaSmile.apartSearch.databaseClass.userModel.UserModelVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController extends PrimaryController implements Initializable {


    // field
    // SuSb == Sign Up Sub-View
    @FXML
    private TextField idSuSbTextField;
    @FXML
    private PasswordField pwSuSbPasswordField;
    @FXML
    private PasswordField confirmPwSuSbPasswordField;
    @FXML
    private Text signUpConsequenceTextSuSbView;


    @FXML
    private void handleSubmitButtonSuSbViewAction() {

        String getID = idSuSbTextField.getText();
        String getPW1 = pwSuSbPasswordField.getText();
        String getPW2 = confirmPwSuSbPasswordField.getText();

        if (getID.isEmpty() || getID.isBlank()) {
            signUpConsequenceTextSuSbView.setText("ID 입력 값이 없습니다.");
            signUpConsequenceTextSuSbView.setVisible(true);
            return;
        }

        if (getPW1.isBlank() || getPW1.isBlank() || getPW2.isEmpty() || getPW2.isBlank()) {
            signUpConsequenceTextSuSbView.setText("패스워드 입력 값이 없습니다.");
            signUpConsequenceTextSuSbView.setVisible(true);
            return;
        }

        if (!getPW1.equals(getPW2)) {
            signUpConsequenceTextSuSbView.setText("패스워드와 패스워드 확인이 일치하지 않습니다.");
            signUpConsequenceTextSuSbView.setVisible(true);
            return;
        }


        UserLoginModelDAO loginDAO = new UserLoginModelDAO();
        UserModelVO existUserInfo = loginDAO.loginUser(getID, getPW1);

        // check whether ID exists in the database
        // userInfo.getUserPrimaryKey() == -1 -> getID can be new user ID
        if (existUserInfo.getUserPrimaryKey() != -1) {
            signUpConsequenceTextSuSbView.setText("이미 존재하는 ID입니다");
            signUpConsequenceTextSuSbView.setVisible(true);
            return;
        }

        UserSignUpModelDAO signUpDAO = new UserSignUpModelDAO();
        boolean isSignUpSucceed = signUpDAO.signUpUser(getID, getPW1);

        if (isSignUpSucceed) {
            signUpConsequenceTextSuSbView.setText(getID + "계정이 생성되었습니다.");
            signUpConsequenceTextSuSbView.setVisible(true);
            setStatusDisplayText("유저 생성 완료");
        } else {
            signUpConsequenceTextSuSbView.setText("계정 생성이 실패했습니다.");
            signUpConsequenceTextSuSbView.setVisible(true);

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpConsequenceTextSuSbView.setVisible(false);
    }
}
