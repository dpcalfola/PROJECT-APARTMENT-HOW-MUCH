package com.folaSmile.apartSearch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {

    static MainController mainControllerHandle;


    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        
        // load font 강원교육튼튼체
        Font.loadFont(getClass().getResourceAsStream("font/GangwonEduPower ExtraBold.ttf"), 10);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = loader.load();

        // make controller static
        mainControllerHandle = (MainController) loader.getController();

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