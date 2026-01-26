package paginacion;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;

public class PaginationController {

    @FXML
    private Pagination pagination;

    @FXML
    private ProgressBar progressBar;

    private ArrayList<String> nombres = new ArrayList<>();
    private static final int ITEMS_PER_PAGE = 5;


    @FXML
    private void initialize() {
        this.initNombres(this.nombres);

        pagination.setPageCount((nombres.size() / ITEMS_PER_PAGE) + (((nombres.size() % ITEMS_PER_PAGE) > 0) ? 1 : 0));

        pagination.setPageFactory((Integer pageIndex) ->);
    }

}
