package application;

import databaseClass.user.UserLoginDAO;
import databaseClass.user.UserSignUpDAO;
import databaseClass.user.UserVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


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

        System.out.println("Sign up subView : submit button clicked");

        String getID = idSuSbTextField.getText();
        String getPW1 = pwSuSbPasswordField.getText();
        String getPW2 = confirmPwSuSbPasswordField.getText();

        if (!getPW1.equals(getPW2)) {
            signUpConsequenceTextSuSbView.setText("Those password didn't match");
            signUpConsequenceTextSuSbView.setVisible(true);
        } else {

            System.out.println("Those password match");
            UserLoginDAO loginDAO = new UserLoginDAO();
            UserVO existUserInfo = loginDAO.loginUser(getID, getPW1);

            // check whether ID exists in the database
            // userInfo.getUserPrimaryKey() == -1 -> getID can be new user ID
            if (existUserInfo.getUserPrimaryKey() != -1){
                signUpConsequenceTextSuSbView.setText("ID already exists.");
                signUpConsequenceTextSuSbView.setVisible(true);
            }else{
                UserSignUpDAO signUpDAO = new UserSignUpDAO();
                UserVO userInfo = signUpDAO.SignUpUser(getID, getPW1);
                String newUserID = userInfo.getUserID();
                System.out.println(newUserID + " made successfully");
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpConsequenceTextSuSbView.setVisible(false);
    }
}
