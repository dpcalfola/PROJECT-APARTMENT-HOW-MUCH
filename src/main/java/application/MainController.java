package application;

import databaseClass.tableModel.ConstraintModelVO;
import databaseClass.testConnection.TestConnection;
import databaseClass.userModel.UserLoginConfirmTest;

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


    // Constraints text fields
    @FXML
    private TextField keywordTextField;
    // Constraints text fields (Integer input)
    @FXML
    private TextField minPriceTextField;
    @FXML
    private TextField maxPriceTextField;
    @FXML
    private TextField minAreaTextField;
    @FXML
    private TextField maxAreaTextField;
    @FXML
    private TextField minContractDateTextField;
    @FXML
    private TextField maxContractDateTextField;
    @FXML
    private TextField minConstructYearTextField;
    @FXML
    private TextField maxConstructYearTextField;
    @FXML
    private TextField minFloorTextField;
    @FXML
    private TextField maxFloorTextField;


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

        // restrict the input to numbers
        minPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            minPriceTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        maxPriceTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            maxPriceTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        minAreaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            minAreaTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        maxAreaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            maxAreaTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        minContractDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            minContractDateTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        maxContractDateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            maxContractDateTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        minConstructYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            minConstructYearTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        maxConstructYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            maxConstructYearTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        minFloorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            minFloorTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        maxFloorTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            maxFloorTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });

    }


    //Button
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {
//        System.out.println("Table button clicked !!");
//        String getKeywordTextField = keywordTextField.getText();
//        mainControllerConstraintModelVO = new ConstraintModelVO(getKeywordTextField);
//        changeBorderPaneCenterSearch("table-subView.fxml", mainControllerConstraintModelVO);

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

        // put data into ConstraingtModelVO
        mainControllerConstraintModelVO = getConstraintInfo();

        // testCode
        System.out.println("chaek num 1: " + mainControllerConstraintModelVO.getConstraintKeyword());
        //

        //call primary changeBorderPaneCenterSearch method
        changeBorderPaneCenterSearch("table-subView.fxml", mainControllerConstraintModelVO);

    }


    // get information of constraints and return ConstraintModelVo using constructor
    private ConstraintModelVO getConstraintInfo() throws NumberFormatException {


        String getKeywordTextField = keywordTextField.getText();
        String getMinPriceTextField = minPriceTextField.getText();
        String getMaxPriceTextField = maxPriceTextField.getText();
        String getMinAreaTextField = minAreaTextField.getText();
        String getMaxAreaTextField = maxAreaTextField.getText();

        //
        String getMinContractDateTextField = minContractDateTextField.getText();
        String getMaxContractDateTextField = maxContractDateTextField.getText();

        //
        String getMinConstructYearTextField = minConstructYearTextField.getText();
        String getMaxConstructYearTextField = maxConstructYearTextField.getText();
        String getMinFloorTextField = minFloorTextField.getText();
        String getMaxFloorTextField = maxFloorTextField.getText();


        ConstraintModelVO constraintInfo = new ConstraintModelVO(getKeywordTextField, getMinPriceTextField, getMaxPriceTextField, getMinAreaTextField, getMaxAreaTextField, getMinContractDateTextField, getMaxContractDateTextField, getMinConstructYearTextField, getMaxConstructYearTextField, getMinFloorTextField, getMaxFloorTextField);


        return constraintInfo;
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
