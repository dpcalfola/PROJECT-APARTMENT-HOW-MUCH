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


    // Application starts form here !!
    // and this initializing method run only once
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // DB connection test
        TestConnection t1 = new TestConnection();
        t1.testConnect();

        // login confirm test
        UserLoginConfirmTest c1 = new UserLoginConfirmTest();
        c1.confirmUser();

        // make constraints text field be gotten only numbers ( without keyword )
        restrictInputValueOnConstraintTextField();

        // -1 means guest
        setLoggedInUserKey(-1);

    }


    // Buttons located top pane
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {

        // put data into ConstraintModelVO
        mainControllerConstraintModelVO = getConstraintInfo();

        //call primary changeBorderPaneCenterSearch method
        drawDataTableOnCenter(mainControllerConstraintModelVO);
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        changeBorderPaneCenter("login-subView.fxml");
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        System.out.println("Logout Button clicked !!");
    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException {
        changeBorderPaneCenter("signup-subView.fxml");
    }


    // This button located bottom of constraints field
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {

        // put data into ConstraintModelVO
        mainControllerConstraintModelVO = getConstraintInfo();

        // Call method form primaryModel
        // Throw ConstraintModelVO to PrimaryModel
        drawDataTableOnCenter(mainControllerConstraintModelVO);

    }


    // get information of constraints and return ConstraintModelVo using constructor
    private ConstraintModelVO getConstraintInfo() throws NumberFormatException {

        // don't need length checker
        String getKeywordTextField = keywordTextField.getText();
        String getMinPriceTextField = minPriceTextField.getText();
        String getMaxPriceTextField = maxPriceTextField.getText();
        String getMinAreaTextField = minAreaTextField.getText();
        String getMaxAreaTextField = maxAreaTextField.getText();
        //


        // lengthChecker method (_: String, _: int)
        // If the input doesn't match with digit return null-string,
        // otherwise it returns parameter String

        // *and set warning message (개발 예정)

        // ContractDateTextField should be 8 digit
        String getMinContractDateTextField = lengthChecker(minContractDateTextField.getText(), 8);
        String getMaxContractDateTextField = lengthChecker(maxContractDateTextField.getText(), 8);


        // ConstructYearTextField should be 4 digit
        String getMinConstructYearTextField = lengthChecker(minConstructYearTextField.getText(), 4);
        String getMaxConstructYearTextField = lengthChecker(maxConstructYearTextField.getText(), 4);


        // Floor doesn't need lengthChecker
        String getMinFloorTextField = minFloorTextField.getText();
        String getMaxFloorTextField = maxFloorTextField.getText();


        return new ConstraintModelVO(
                getKeywordTextField,
                getMinPriceTextField,
                getMaxPriceTextField,
                getMinAreaTextField,
                getMaxAreaTextField,
                getMinContractDateTextField,
                getMaxContractDateTextField,
                getMinConstructYearTextField,
                getMaxConstructYearTextField,
                getMinFloorTextField,
                getMaxFloorTextField);
    }


    private void restrictInputValueOnConstraintTextField() {
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


    private String lengthChecker(String s, int digit) {
        return s.length() == digit ? s : "";
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

    // DO NOT REMOVE THIS METHOD !!
    @Override
    public TextField getSearchTextField() {
        return searchTextField;
    }

    // getter
    public ConstraintModelVO getMainControllerConstraintModelVO() {
        return mainControllerConstraintModelVO;
    }
}
