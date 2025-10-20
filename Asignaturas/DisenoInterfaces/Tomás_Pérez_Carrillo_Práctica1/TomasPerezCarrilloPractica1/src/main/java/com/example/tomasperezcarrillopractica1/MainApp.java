package com.example.tomasperezcarrillopractica1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class MainApp extends Application {

    // variable estática para guardar qué pantalla abrir
    private static String fxmlFile = "socios_listado.fxml";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Gestión Gimnasio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Selecciona pantalla a abrir ===");
        System.out.println("1 - Listado de Socios");
        System.out.println("2 - Mantenimiento de Socios");
        System.out.println("3 - Ayuda / Tutorial");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();

        switch (opcion) {
            case 1 -> fxmlFile = "Vistas/socios_listado.fxml";
            case 2 -> fxmlFile = "Vistas/socios_mantenimiento.fxml";
            case 3 -> fxmlFile = "Vistas/socios_ayuda.fxml";
            default -> {
                System.out.println("Opción no válida. Se abrirá Listado de Socios por defecto.");
                fxmlFile = "socios_listado.fxml";
            }
        }

        launch();
    }
}
