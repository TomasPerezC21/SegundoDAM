package choice;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class ChoiceController {

    @FXML
    private Button btnMostrar;

    @FXML
    private ListView<String> listRes;

    @FXML
    private void initialize() {
        // Inicializamos el listado con 10 items y para seleccionar varios a la vez
        for (int i = 1; i <= 10; i++) {
            listRes.getItems().add("Opción " + i);
        }
        listRes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Boton deshabilitado por defecto
        btnMostrar.setDisable(true);

        // No hace falta crear un listener, solo se añade para practicar
        // Con bind asociamos directamente la propiedad disable al número de Items seleccionado en el ListView
        // Prueba a descomentar el código de debajo y a comentar el listener para practicar
        // btnMostrar.disableProperty().bind(Bindings.isEmpty(listRes.getSelectionModel().getSelectedItems()));

        // Cada vez que hay un cambio en los items seleccionados, se actualiza el listado que se mostrará en el ChoiceDialog
        listRes.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends String> change) -> {

            // Se modifica directamente la propiedad disable en función de si hay Items seleccionados o no
            btnMostrar.setDisable(change.getList().isEmpty());
        });
    }

    @FXML
    private void mostrarDialogo(ActionEvent event) {
        ObservableList<String> olChoice = listRes.getSelectionModel().getSelectedItems();

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(olChoice.get(0), olChoice);
        choiceDialog.initModality(Modality.APPLICATION_MODAL);
        choiceDialog.setTitle("Ejemplo dialogo");
        choiceDialog.setHeaderText("Seleccione un valor...");

        choiceDialog.showAndWait().ifPresent(s -> {
            listRes.getSelectionModel().clearSelection();
            listRes.getSelectionModel().select(s);
        });
    }

    @FXML
    private void avisarEmpty(MouseEvent event) {

        if (listRes.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert errorAlter = new Alert(Alert.AlertType.INFORMATION);
            errorAlter.setTitle("Avisar sobre listado");
            errorAlter.setHeaderText("Sin items seleccionados");
            errorAlter.setContentText("No se puede mostar el diálogo hasta que se seleccione un item como mínimo");
            errorAlter.showAndWait();
        }
    }
}
