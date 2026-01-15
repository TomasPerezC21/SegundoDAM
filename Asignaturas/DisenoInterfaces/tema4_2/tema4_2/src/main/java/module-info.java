module org.example.tema4_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tema4_2 to javafx.fxml;
    exports org.example.tema4_2;
}