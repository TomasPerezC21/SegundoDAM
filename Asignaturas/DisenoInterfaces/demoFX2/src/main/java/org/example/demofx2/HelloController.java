package org.example.demofx2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Tira!");
    }

    @FXML
    protected void onHelloButtonClick2() {
        welcomeText.setText("Vengaaa!");
    }
}
