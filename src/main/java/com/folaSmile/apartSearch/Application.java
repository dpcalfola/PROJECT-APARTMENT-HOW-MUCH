package com.folaSmile.apartSearch;

import com.folaSmile.apartSearch.databaseClass.testConnection2.TestConnectionDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Application extends javafx.application.Application {

    static MainController mainControllerHandle;


    @Override
    public void start(Stage primaryStage) throws IOException {

        // load font 강원교육튼튼체
        Font.loadFont(getClass().getResourceAsStream("font/GangwonEduPower ExtraBold.ttf"), 10);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();


        // make controller static
        mainControllerHandle = (MainController) loader.getController();


        // check up database connection
        TestConnectionDAO testDao = new TestConnectionDAO();
        int testConnectResult = testDao.testConnect(777);
        if (testConnectResult == 486486) {
            System.out.println("Connect successfully");
            mainControllerHandle.setStatusDisplayText("데이터베이스 연결에 성공했습니다.");
        } else {
            System.out.println("connection failed");
            mainControllerHandle.setStatusDisplayText("데이터베이스 연결에 실패했습니다.");
        }

        // set information page
        Pane view;
        view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("information-subView.fxml")));
        mainControllerHandle.getMainPane().setCenter(view);


        Scene scene = new Scene(root);
        primaryStage.setTitle("Apartment How Much");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch();
    }


}