package org.example.tutorial1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private Label nombre;


    protected void onNameButtonClick() {

        nombre.setText("Tomás");


    }
}
