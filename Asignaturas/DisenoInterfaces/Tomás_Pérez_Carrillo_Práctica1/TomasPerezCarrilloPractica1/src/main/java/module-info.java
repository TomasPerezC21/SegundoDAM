module com.example.tomasperezcarrillopractica1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tomasperezcarrillopractica1 to javafx.fxml;
    exports com.example.tomasperezcarrillopractica1;
}