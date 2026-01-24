package dragFiles;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class DragFilesController {

    @FXML
    private Text source;

    @FXML
    private Text target;

    @FXML
    private ImageView imageView;

    @FXML
    private void initialize() {

    }

    /*Los dos primaros metodos permiten aceptar ficheros del explorador */

    @FXML
    private void handleDragDrop(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        Image img = null;
        try {
            img = new Image(new FileInputStream(files.get(0)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imageView.setImage(img);
    }

    //Para detectar que se puede arrastrar el texto origen
    @FXML
    private void handleDragDetection(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(source.getText());

        db.setContent(content);
    }

    //Para detectar el texto orogen en el destino
    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleTextDropped(DragEvent event) {
        if (event.getDragboard().hasString()) {
            String str =  event.getDragboard().getString();
            target.setText(str);
            //Se marca la operacion como completada para el evento OnDragDone
            event.setDropCompleted(true);
        }
    }

    @FXML
    private void handleDragEntered(DragEvent event) {
        //Se comprueba previamente en el objeto Dragboard si se ha arrastrado contenido
        if (event.getTransferMode() != null) {
            source.setText("Operación drag terminada");
        }
    }


}