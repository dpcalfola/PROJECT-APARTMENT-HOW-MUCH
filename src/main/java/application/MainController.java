package application;

import databaseClass.table.ConstraintModelVO;
import databaseClass.testConnection.TestConnection;
import databaseClass.user.UserLoginConfirmTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends PrimaryModel implements Initializable {


    //Field
    @FXML
    private Text greetingTextField;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Text statusDisplayText;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField keywordTextField;


    ConstraintModelVO mainControllerConstraintModelVO;

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // DB connection test
        TestConnection t1 = new TestConnection();
        t1.testConnect();

        // login confirm test
        UserLoginConfirmTest c1 = new UserLoginConfirmTest();
        c1.confirmUser();
    }


    //Button
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {
        System.out.println("Table button clicked !!");
        String getKeywordTextField = keywordTextField.getText();
        mainControllerConstraintModelVO = new ConstraintModelVO(getKeywordTextField);
        changeBorderPaneCenterSearch("table-subView.fxml", mainControllerConstraintModelVO);

    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        System.out.println("Login Button clicked !!");
        changeBorderPaneCenter("login-subView.fxml");
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        System.out.println("Logout Button clicked !!");
    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException {
        System.out.println("SignUp Button clicked !!");
        changeBorderPaneCenter("signup-subView.fxml");
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {
        System.out.println("Search Button clicked !!");
        String getKeywordTextField = keywordTextField.getText();
        mainControllerConstraintModelVO = new ConstraintModelVO(getKeywordTextField);
        System.out.println("chaek num 1: " + mainControllerConstraintModelVO.getConstraintKeyword());
        changeBorderPaneCenterSearch("table-subView.fxml", mainControllerConstraintModelVO);

//        System.out.println("getKeywordTextField: " + getKeywordTextField);

    }


    // FXML getter
    public BorderPane getMainPane() {
        return mainPane;
    }

    public Text getGreetingTextField() {
        return greetingTextField;
    }

    public Text getStatusDisplayText() {
        return statusDisplayText;
    }

    public TextField getSearchTextField() {
        return searchTextField;
    }

    // getter
    public ConstraintModelVO getMainControllerConstraintModelVO() {
        return mainControllerConstraintModelVO;
    }
}
