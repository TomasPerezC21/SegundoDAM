package org.example.unidad9_ejercicioscss_xml.flowpanecss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ejercicio1Horizontal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FlowPaneLayoutHorizontal.fxml"));
        primaryStage.setTitle("Ejercicio 1 - FlowPane CSS Vertical");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
