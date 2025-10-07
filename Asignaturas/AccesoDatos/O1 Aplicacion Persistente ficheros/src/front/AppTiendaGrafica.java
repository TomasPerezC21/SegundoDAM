package front;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppTiendaGrafica extends Application {
    private BorderPane root = new BorderPane();
    private Inventario inventario = new Inventario();

    public AppTiendaGrafica() throws IOException {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        try{
            inventario = new Inventario();
        }catch (Exception e){
            showError("Error de entrada salida al fichero de texto: " + e.getMessage());
        }

        MenuBar menuBar = crearMenu();

        root.setTop(menuBar);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventario Tienda Online");
        primaryStage.show();
    }

    private MenuBar crearMenu() {
        Menu menu = new Menu("Opciones");

        MenuItem insertar = new MenuItem("Insertar producto.");
        MenuItem vender = new MenuItem("Vender producto.");
        MenuItem reponer = new MenuItem("Reponer producto.");
        MenuItem mostrar = new MenuItem("Mostrar inventario.");
        MenuItem salir = new MenuItem("Salir y guardar.");

        insertar.setOnAction(e -> root.setCenter(pantallaInsertarProducto()));
        vender.setOnAction(e -> root.setCenter(pantallaVenderReponer(true)));
        reponer.setOnAction(e -> root.setCenter(pantallaVenderReponer(false)));
        mostrar.setOnAction(e -> root.setCenter(pantallaMostrarInventario()));
        salir.setOnAction(e -> {
            try {
                inventario.guardarDatosFichero();
            } catch (IOException ex) {
                showError("Error de E/S a fichero: " + ex.getMessage());
            }
            System.exit(0);
        });

        menu.getItems().addAll(insertar, vender, reponer, mostrar, salir);

        return new MenuBar(menu);
    }

    private GridPane pantallaInsertarProducto() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<String> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll("Electronico", "Ropa");
        TextField id=new TextField();
        TextField nombre = new TextField();
        TextField precio = new TextField();
        TextField stock = new TextField();

        TextField campoExtra1 = new TextField(); // marca / talla
        TextField campoExtra2 = new TextField(); // garantía / material

        Label l0 = new Label("Tipo:");
        Label l1=new Label("Id");
        Label l2 = new Label("Nombre:");
        Label l3 = new Label("Precio:");
        Label l4 = new Label("Stock:");
        Label l5 = new Label("Marca / Talla:");
        Label l6 = new Label("Garantía / Material:");

        Button btn = new Button("Guardar");
        btn.setOnAction(e -> {
            String tipo = tipoBox.getValue();
            int idProducto=Integer.parseInt(id.getText());
            String nom = nombre.getText();
            double pre = Double.parseDouble(precio.getText());
            int sto = Integer.parseInt(stock.getText());
            String ex1 = campoExtra1.getText();
            String ex2 = campoExtra2.getText();
            if (tipo.equals("Electronico")) {
                Electronico prodElectronico = new Electronico(idProducto, nom, pre, sto, ex1, Integer.parseInt(ex2));
                if (inventario.insertarProducto(prodElectronico)) {
                    showInfo("Producto insertado correctamente.");
                }
                else{
                    showInfo("Error. El producto con ese id ya existe.");
                }
            }
            else{
                Ropa prodRopa = new Ropa(idProducto, nom, pre, sto, ex1, ex2);
                if (inventario.insertarProducto(prodRopa)) {
                    showInfo("Producto insertado correctamente.");
                }
                else{
                    showInfo("Error. El producto con ese id ya existe.");
                }
            }
        });

        grid.addRow(0, l0, tipoBox);
        grid.addRow(1, l1, id);
        grid.addRow(2, l2, nombre);
        grid.addRow(3, l3, precio);
        grid.addRow(4, l4, stock);
        grid.addRow(5, l5, campoExtra1);
        grid.addRow(6, l6, campoExtra2);
        grid.add(btn, 1, 7);

        return grid;
    }
    private VBox pantallaMostrarInventario() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));
        TableView<Producto> table = new TableView<>();

        TableColumn<Producto, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Producto, String> tipoCol = new TableColumn<>("Tipo");
        tipoCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue() instanceof Electronico ? "Electronico" : "Ropa"));

        TableColumn<Producto, Number> precioCol = new TableColumn<>("Precio");
        precioCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrecio()));

        TableColumn<Producto, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStock()));

        table.getColumns().addAll(nombreCol, tipoCol, precioCol, stockCol);

        ArrayList<Producto> listaProductos = inventario.getListaProductos();
        for (Producto producto : listaProductos) {
            table.getItems().add(producto);
        }

        box.getChildren().add(table);
        return box;
    }

    private VBox pantallaVenderReponer(boolean esVenta) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        ComboBox<Producto> combo = new ComboBox<>();

        ArrayList<Producto> listaProductos = inventario.getListaProductos();
        for (Producto producto : listaProductos) {
            combo.getItems().add(producto);
        }

        TextField cantidadField = new TextField();
        Button btn = new Button(esVenta ? "Vender" : "Reponer");



        btn.setOnAction(e -> {
            Producto p = combo.getValue();
            int cantidad = Integer.parseInt(cantidadField.getText());
            if (esVenta) {
                if (inventario.venderProducto(p.getId(), cantidad)){
                    showInfo("Venta realizada correctamente");
                }
                else{
                    showInfo("Error. Venta no realizada. No hay stock.");
                }
            }
            else{
                if (inventario.reponerProducto(p.getId(), cantidad)){
                    showInfo("Producto repuesto correctamente");
                }
            }

        });

        box.getChildren().addAll(new Label("Producto:"), combo, new Label("Cantidad:"), cantidadField, btn);
        return box;
    }


    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }

}