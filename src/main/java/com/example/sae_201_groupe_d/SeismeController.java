package com.example.sae_201_groupe_d;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SeismeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}