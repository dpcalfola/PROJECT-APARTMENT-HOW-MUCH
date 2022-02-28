package application;

import databaseClass.tableModel.ConstraintModelVO;
import databaseClass.testConnection.TestConnection;
import databaseClass.userModel.UserLoginConfirmTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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


    // Button of top pane
    @FXML
    private Button tableButton;
    @FXML
    private Button bookmarkButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button signUpButton;


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

        // initialize: loggedInUserKey value
        // -1 means guest
        setLoggedInUserKey(-1);

        // initialize: bookmark status
        setOnBookmark(false);

        // initialize: set button property
        bookmarkButton.setOpacity(0.5);
        bookmarkButton.setDisable(true);
        logoutButton.setOpacity(0.5);
        logoutButton.setDisable(true);
    }


    // Buttons located top pane

    // 전체 조회 버튼
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {

        // turn off boolean onBookmark
        setOnBookmark(false);

        // Draw table 
        mainControllerConstraintModelVO = getConstraintInfo();
        drawDataTableOnCenter(mainControllerConstraintModelVO);
    }

    // 북마크(조회) 버튼
    @FXML
    private void handleBookmarkButtonAction(ActionEvent event) throws IOException {

        // turn on boolean onBookmark
        setOnBookmark(true);

        // Draw table
        mainControllerConstraintModelVO = getConstraintInfo();
        drawDataTableOnCenter(mainControllerConstraintModelVO);
    }

    // 로그인 버튼
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        changeBorderPaneCenter("login-subView.fxml");
    }

    // 로그아웃 버튼
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        changeBorderPaneCenter("logout-subView.fxml");
        setLoggedInUserKey(-1);
        setButtonsAfterLogout();
    }

    // 회원 가입 버튼
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException {
        changeBorderPaneCenter("signup-subView.fxml");
    }


    // 검색 버튼 ( draw table with current boolean onBookmark )
    // This button located bottom of constraints field
    @FXML
    private void handleSearchButtonAction(ActionEvent event) throws IOException {

        // Draw table
        mainControllerConstraintModelVO = getConstraintInfo();
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


    // FXML field getter
    public BorderPane getMainPane() {
        return mainPane;
    }

    public Text getGreetingTextField() {
        return greetingTextField;
    }

    public Text getStatusDisplayText() {
        return statusDisplayText;
    }

    // FXML buttons getter
    public Button getTableButton() {
        return tableButton;
    }

    public Button getBookmarkButton() {
        return bookmarkButton;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getSignUpButton() {
        return signUpButton;
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
