package com.example.campsitemanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CampsiteController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
