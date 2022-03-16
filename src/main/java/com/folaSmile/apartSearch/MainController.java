package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseModel.connectionLog.InsertLogDAO;
import com.folaSmile.apartSearch.databaseModel.systemInformation.SystemInformationDAO;
import com.folaSmile.apartSearch.databaseModel.systemInformation.SystemInformationVO;
import com.folaSmile.apartSearch.databaseModel.tableModel.ConstraintModelVO;

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


public class MainController extends PrimaryController implements Initializable {


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
    @FXML
    private Button informationButton;


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


        // Make constraints text field be gotten only int input ( without keyword field )
        restrictInputValueOnConstraintTextField();

        // initialize: getConstraintModelVO -> primaryModel get initial static VO
        mainControllerConstraintModelVO = getConstraintInfo();
        setInitialConstraintVO(getConstraintInfo());


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


        // Remain connection time and client ver information to cloud DB server
        // Connection time is going to be recorded as cloud DB server time
        try {
            SystemInformationDAO sysDAO = new SystemInformationDAO();
            SystemInformationVO sysVO = sysDAO.getSystemInfo();
            String clientVer = sysVO.getClientVer();

            InsertLogDAO logDAO = new InsertLogDAO();
            if (logDAO.insertLog(clientVer)) {
                System.out.println("클라이언트 정보와 접속 시간이 데이터베이스에 기록됩니다. \nSent client ver : " + clientVer);
            } else {
                System.out.println("접속 로그 기록 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    // Buttons located top pane

    // 정보 버튼
    @FXML
    private void handleInformationButtonAction(ActionEvent event) throws IOException {

        // Change button colour ( pressed )
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("pressed-button");

        // Reset button color ( button1 )
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("button1");
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("button1");
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("button1");
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("button1");
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("button1");

        // Reset status message
        setStatusDisplayText("");

        // Change center pane
        changeBorderPaneCenter("information-subView.fxml");

    }

    // 전체 조회 버튼
    @FXML
    private void handleTableButtonAction(ActionEvent event) throws IOException {

        // I don't know why it doesn't work when the button is pressed. WHY???
        setStatusDisplayText("검색을 시작합니다: 전체조회");

        // Change button colour ( pressed )
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("pressed-button");
        // Reset button color ( button1 )
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("button1");
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("button1");
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("button1");
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("button1");
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("button1");


        // turn off boolean onBookmark
        setOnBookmark(false);

        // Draw table 
        mainControllerConstraintModelVO = getConstraintInfo();
        drawDataTableOnCenter(mainControllerConstraintModelVO);
    }

    // 북마크(조회) 버튼
    @FXML
    private void handleBookmarkButtonAction(ActionEvent event) throws IOException {

        setStatusDisplayText("검색을 시작합니다: 북마크");

        // Clear constraint field
        clearConstraintField();


        // Change button color ( pressed )
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("pressed-button");
        // Reset button color ( button1 )
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("button1");
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("button1");
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("button1");
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("button1");
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("button1");


        // turn on boolean onBookmark
        setOnBookmark(true);

        // Draw bookmark table
        mainControllerConstraintModelVO = getConstraintInfo();
        drawBookmarkDataOnCenter(mainControllerConstraintModelVO);

    }

    // 로그인 버튼
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {

        // Change button colour ( pressed )
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("pressed-button");
        // Reset button color ( button1 )
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("button1");
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("button1");
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("button1");
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("button1");
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("button1");

        // Reset status message
        setStatusDisplayText("");

        // Change center pane
        changeBorderPaneCenter("login-subView.fxml");
    }

    // 로그아웃 버튼
    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {


        // Change button colour ( pressed )
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("pressed-button");
        // Reset button color ( button1 )
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("button1");
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("button1");
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("button1");
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("button1");
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("button1");

        // setup guest status
        setLoggedInUserKey(-1);
        setButtonsAfterLogout();
        setGreetingTextField("아파트 실거래가 조회 시스템");


        // Reset status message
        setStatusDisplayText("");

        // Change center pane
        changeBorderPaneCenter("logout-subView.fxml");

    }


    // 회원 가입 버튼
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) throws IOException {

        // Change button colour ( pressed )
        signUpButton.getStyleClass().removeAll("pressed-button", "button1");
        signUpButton.getStyleClass().add("pressed-button");
        // Reset button color ( button1 )
        logoutButton.getStyleClass().removeAll("pressed-button", "button1");
        logoutButton.getStyleClass().add("button1");
        tableButton.getStyleClass().removeAll("pressed-button", "button1");
        tableButton.getStyleClass().add("button1");
        bookmarkButton.getStyleClass().removeAll("pressed-button", "button1");
        bookmarkButton.getStyleClass().add("button1");
        loginButton.getStyleClass().removeAll("pressed-button", "button1");
        loginButton.getStyleClass().add("button1");
        informationButton.getStyleClass().removeAll("pressed-button", "button1");
        informationButton.getStyleClass().add("button1");


        // Reset status message
        setStatusDisplayText("");

        // Change center pane
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
        String getKeywordTextField = keywordTextField.getText().trim();
        String getMinPriceTextField = minPriceTextField.getText().trim();
        String getMaxPriceTextField = maxPriceTextField.getText().trim();
        String getMinAreaTextField = minAreaTextField.getText().trim();
        String getMaxAreaTextField = maxAreaTextField.getText().trim();
        //


        // lengthChecker method (_: String, _: int)
        // If the input doesn't match with digit return null-string,
        // otherwise it returns parameter String

        // *and set warning message (개발 예정)

        // ContractDateTextField should be 8 digit
        String getMinContractDateTextField = lengthChecker(minContractDateTextField.getText().trim(), 8);
        String getMaxContractDateTextField = lengthChecker(maxContractDateTextField.getText().trim(), 8);


        // ConstructYearTextField should be 4 digit
        String getMinConstructYearTextField = lengthChecker(minConstructYearTextField.getText().trim(), 4);
        String getMaxConstructYearTextField = lengthChecker(maxConstructYearTextField.getText().trim(), 4);


        // Floor doesn't need lengthChecker
        String getMinFloorTextField = minFloorTextField.getText().trim();
        String getMaxFloorTextField = maxFloorTextField.getText().trim();


        return new ConstraintModelVO(getKeywordTextField, getMinPriceTextField, getMaxPriceTextField, getMinAreaTextField, getMaxAreaTextField, getMinContractDateTextField, getMaxContractDateTextField, getMinConstructYearTextField, getMaxConstructYearTextField, getMinFloorTextField, getMaxFloorTextField);
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

    // Digit number checker
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

    public Button getInformationButton() {
        return informationButton;
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


    // Constraint text fields getter

    public TextField getKeywordTextField() {
        return keywordTextField;
    }

    public TextField getMinPriceTextField() {
        return minPriceTextField;
    }

    public TextField getMaxPriceTextField() {
        return maxPriceTextField;
    }

    public TextField getMinAreaTextField() {
        return minAreaTextField;
    }

    public TextField getMaxAreaTextField() {
        return maxAreaTextField;
    }

    public TextField getMinContractDateTextField() {
        return minContractDateTextField;
    }

    public TextField getMaxContractDateTextField() {
        return maxContractDateTextField;
    }

    public TextField getMinConstructYearTextField() {
        return minConstructYearTextField;
    }

    public TextField getMaxConstructYearTextField() {
        return maxConstructYearTextField;
    }

    public TextField getMinFloorTextField() {
        return minFloorTextField;
    }

    public TextField getMaxFloorTextField() {
        return maxFloorTextField;
    }
}
