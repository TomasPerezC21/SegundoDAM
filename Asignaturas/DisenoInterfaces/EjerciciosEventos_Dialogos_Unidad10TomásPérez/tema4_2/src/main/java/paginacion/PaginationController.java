package paginacion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class PaginationController {

    @FXML
    private Pagination pagination;

    @FXML
    private ProgressBar progressBar;

    private ArrayList<String> nombres = new ArrayList<>();

    private static final int ITEMS_PER_PAGE = 5;

    @FXML
    private void initialize() {

        this.initNombres();


        int totalPageCount = (nombres.size() / ITEMS_PER_PAGE) + ((nombres.size() % ITEMS_PER_PAGE > 0) ? 1 : 0);
        pagination.setPageCount(totalPageCount);


        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));


        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            updateProgressBar(newIndex.intValue(), totalPageCount);
        });

        updateProgressBar(0, totalPageCount);
    }


    private Node createPage(int pageIndex) {
        VBox box = new VBox(10); // Espacio de 10px entre elementos

        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, nombres.size());

        List<String> pageItems = nombres.subList(fromIndex, toIndex);

        for (String nombre : pageItems) {
            Label label = new Label(nombre);
            box.getChildren().add(label);
        }

        return box;
    }

    private void updateProgressBar(int index, int totalPages) {
        double progress = (double) (index + 1) / totalPages;
        progressBar.setProgress(progress);
    }

    private void initNombres() {
        // Generamos 28 nombres para tener varias páginas (como en el ejemplo del PDF)
        for (int i = 1; i <= 28; i++) {
            nombres.add(i + ".- Persona Generada " + i);
        }
    }
}