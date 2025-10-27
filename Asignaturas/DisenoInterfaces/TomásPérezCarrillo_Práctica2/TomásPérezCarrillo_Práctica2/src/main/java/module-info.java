module org.example.alvaroguy_practica2_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.alvaroguy_practica2_1 to javafx.fxml;
    exports org.example.alvaroguy_practica2_1;
}