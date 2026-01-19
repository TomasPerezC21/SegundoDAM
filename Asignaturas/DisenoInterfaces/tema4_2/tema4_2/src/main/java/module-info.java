module org.example.tema4_2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens eventosValidar to javafx.fxml;
    exports eventosValidar;

    opens dragFiles to javafx.fxml;
    exports dragFiles;
}