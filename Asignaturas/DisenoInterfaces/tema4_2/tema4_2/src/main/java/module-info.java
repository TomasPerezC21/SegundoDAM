module org.example.tema4_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens eventosValidar to javafx.fxml;
    exports eventosValidar;

    opens dragFiles to javafx.fxml;
    exports dragFiles;

    opens choice to javafx.fxml;
    exports choice;
}