package eventosValidar;

import javafx.application.Application; // IMPORT ADDED
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

// FIX 1: Must extend Application
public class MainEventosValidar extends Application {

    private VBox rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainEventosValidar.class.getResource("EventosValidar.fxml"));

            rootLayout = (VBox) fxmlLoader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Eventos Validar");
            primaryStage.show();

            crearDialogo(primaryStage, scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void crearDialogo(Stage primaryStage, Scene scene) {

        TextInputDialog dialog = new TextInputDialog("Texto de ejemplo...");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Ejemplo de dialogo");
        dialog.setHeaderText("Dialogo para introducir un texto");


        dialog.getEditor().setText("");
        dialog.getEditor().setPromptText("Introduzca un texto");




        Button btnMostrar = (Button) scene.lookup("#btnMostrar");
        TextField txtResultado  = (TextField) scene.lookup("#txtResultado");

        btnMostrar.setOnAction((event) -> {
           dialog.showAndWait().ifPresent(result -> {
               txtResultado.setText(result);
           });
        });

        dialog.setOnHidden((event) -> {
            txtResultado.requestFocus();
        });

        txtResultado.setOnMouseEntered((event) -> {
            txtResultado.setCursor(Cursor.HAND);
        });




    }
}