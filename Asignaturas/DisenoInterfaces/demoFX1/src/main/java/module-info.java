module org.example.demofx1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.demofx1 to javafx.fxml;
    exports org.example.demofx1;
}