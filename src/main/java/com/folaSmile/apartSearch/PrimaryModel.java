package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseClass.tableModel.ConstraintModelVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

import static com.folaSmile.apartSearch.Application.mainControllerHandle;

public class PrimaryModel {


    // Static field
    private static ConstraintModelVO staticConstraintModelVO;

    private static String loggedInUserID;

    // loggedInUserKey has some value always
    // -1 : guest
    // ect : userPrimaryKey from MySQL
    private static int loggedInUserKey;

    // status bookmark or not
    private static boolean onBookmark;

    // initialize static constraintVO
    static void setInitialConstraintVO(ConstraintModelVO vo) {
        staticConstraintModelVO = vo;
    }


    // static void
    // get constraint values -> setting static field "primaryModelConstraintModelVO" and...
    // Draw data table on center pane
    static void drawDataTableOnCenter(ConstraintModelVO constraintModelVO) throws IOException {


        // Set static property
        staticConstraintModelVO = constraintModelVO;

        // draw center data table
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(PrimaryModel.class.getResource("table-subView.fxml")));
        mainControllerHandle.getMainPane().setCenter(view);

        // After center pane is loaded by this method,
        // TableController will call TableModelDAO

    }

    /// Draw bookmark search
    // reset constraint condition
    void drawBookmarkDataOnCenter() throws IOException {


        // Reset constraint fields value on view
        clearConstraintField();


        // draw center data table
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(PrimaryModel.class.getResource("table-subView.fxml")));
        mainControllerHandle.getMainPane().setCenter(view);


    }

    // overloading
    void drawBookmarkDataOnCenter(ConstraintModelVO constraintModelVO) throws IOException {


        // Reset constraint fields value on view
        clearConstraintField();

        //
        staticConstraintModelVO = constraintModelVO;


        // draw center data table
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(PrimaryModel.class.getResource("table-subView.fxml")));
        mainControllerHandle.getMainPane().setCenter(view);


    }


    // Method which changes center pane
    void changeBorderPaneCenter(String fxmlFile) throws IOException {
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
        mainControllerHandle.getMainPane().setCenter(view);
    }

    // set main view field property
    void setGreetingTextField(String text) {
        mainControllerHandle.getGreetingTextField().setText(text);
    }

    void setStatusDisplayText(String text) {
        mainControllerHandle.getStatusDisplayText().setText(text);
    }


    // Clear constraint field
    void clearConstraintField() {

        mainControllerHandle.getKeywordTextField().setText("");
        mainControllerHandle.getMinPriceTextField().setText("");
        mainControllerHandle.getMaxPriceTextField().setText("");
        mainControllerHandle.getMinAreaTextField().setText("");
        mainControllerHandle.getMaxAreaTextField().setText("");
        mainControllerHandle.getMaxContractDateTextField().setText("");
        mainControllerHandle.getMinContractDateTextField().setText("");
        mainControllerHandle.getMinConstructYearTextField().setText("");
        mainControllerHandle.getMaxConstructYearTextField().setText("");
        mainControllerHandle.getMinFloorTextField().setText("");
        mainControllerHandle.getMaxFloorTextField().setText("");

    }


    // after login
    void setButtonsAfterLogin() {
        // 로그인 버튼 비활성화
        mainControllerHandle.getLoginButton().setOpacity(0.5);
        mainControllerHandle.getLoginButton().setDisable(true);
        // 회원가입 버튼 비활성화
        mainControllerHandle.getSignUpButton().setOpacity(0.5);
        mainControllerHandle.getSignUpButton().setDisable(true);
        // 북마크 버튼 활성화
        mainControllerHandle.getBookmarkButton().setOpacity(1);
        mainControllerHandle.getBookmarkButton().setDisable(false);
        // 로그아웃 버튼 활성화
        mainControllerHandle.getLogoutButton().setOpacity(1);
        mainControllerHandle.getLogoutButton().setDisable(false);
    }

    // After logout
    void setButtonsAfterLogout() {
        // 로그아웃 버튼 비활성화
        mainControllerHandle.getLogoutButton().setOpacity(0.5);
        mainControllerHandle.getLogoutButton().setDisable(true);
        // 북마크 버튼 비활성화
        mainControllerHandle.getBookmarkButton().setOpacity(0.5);
        mainControllerHandle.getBookmarkButton().setDisable(true);
        // 로그인 버튼 활성화
        mainControllerHandle.getLoginButton().setOpacity(1);
        mainControllerHandle.getLoginButton().setDisable(false);
        // 회원가입 버튼 활성화
        mainControllerHandle.getSignUpButton().setOpacity(1);
        mainControllerHandle.getSignUpButton().setDisable(false);
    }


    // TableController use this method
    @FXML
    TextField getSearchTextField() {
        return mainControllerHandle.getSearchTextField();
    }

    // TableController use this method
    public ConstraintModelVO getStaticModelConstraintModelVO() {
        return staticConstraintModelVO;
    }

    // static String loggedInUserId getter and setter
    public static String getLoggedInUserID() {
        return loggedInUserID;
    }

    public static void setLoggedInUserID(String loggedInUserID) {
        PrimaryModel.loggedInUserID = loggedInUserID;
    }

    // static int loggedInUserKey getter and setter
    public static int getLoggedInUserKey() {
        return loggedInUserKey;
    }

    public static void setLoggedInUserKey(int loggedInUserKey) {
        PrimaryModel.loggedInUserKey = loggedInUserKey;
    }

    // static boolean onBookmark getter and setter
    public static boolean isOnBookmark() {
        return onBookmark;
    }

    public static void setOnBookmark(boolean onBookmark) {
        PrimaryModel.onBookmark = onBookmark;
    }


}




















