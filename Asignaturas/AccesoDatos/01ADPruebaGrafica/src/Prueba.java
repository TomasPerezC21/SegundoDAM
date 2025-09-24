import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class Prueba extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Menu menu = new Menu();
        MenuItem opcion1 = new MenuItem("Insertar Producto");
        MenuItem opcion2 = new MenuItem("Mostrar Inventario");
        MenuItem opcion3 = new MenuItem("Vender Producto");
        MenuItem opcion4 = new MenuItem("Reponer producto");
        menu.getMenu().addA




        Scene escena = new Scene(root, 300, 300);
        stage.setScene(escena);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
