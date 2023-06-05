module com.example.sae_201_groupe_d {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;

    opens com.example.sae_201_groupe_d to javafx.fxml;
    exports com.example.sae_201_groupe_d;
}