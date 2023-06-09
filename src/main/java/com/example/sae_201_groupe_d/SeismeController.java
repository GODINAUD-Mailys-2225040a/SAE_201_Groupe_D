package com.example.sae_201_groupe_d;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import com.gluonhq.maps.MapView;


public class SeismeController {

    @FXML
    private MapView mapView;

    @FXML
    private ComboBox<String> dep;

    ObservableList<String> options = FXCollections.observableArrayList(
                    "Ain",
                    "Aisne",
                    "Allier",
                    "Alpes-de-Haute-Provence",
                    "Hautes-Alpes",
                    "Alpes-Maritimes",
                    "Ardèche",
                    "Ardennes",
                    "Ariège",
                    "Aube",
                    "Aude",
                    "Aveyron",
                    "Bouches-du-Rhône",
                    "Calvados",
                    "Cantal",
                    "Charente",
                    "Charente-Maritime",
                    "Cher",
                    "Corrèze",
                    "Côte-d'Or",
                    "Côtes-d'Armor",
                    "Creuse",
                    "Dordogne",
                    "Doubs",
                    "Drôme",
                    "Eure",
                    "Eure-et-Loir",
                    "Finistère",
                    "Corse-du-Sud",
                    "Haute-Corse",
                    "Gard",
                    "Haute-Garonne",
                    "Gers",
                    "Gironde",
                    "Hérault",
                    "Ille-et-Vilaine",
                    "Indre",
                    "Indre-et-Loire",
                    "Isère",
                    "Jura",
                    "Landes",
                    "Loir-et-Cher",
                    "Loire",
                    "Haute-Loire",
                    "Loire-Atlantique",
                    "Loiret",
                    "Lot",
                    "Lot-et-Garonne",
                    "Lozère",
                    "Maine-et-Loire",
                    "Manche",
                    "Marne",
                    "Haute-Marne",
                    "Mayenne",
                    "Meurthe-et-Moselle",
                    "Meuse",
                    "Morbihan",
                    "Moselle",
                    "Nièvre",
                    "Nord",
                    "Oise",
                    "Orne",
                    "Pas-de-Calais",
                    "Puy-de-Dôme",
                    "Pyrénées-Atlantiques",
                    "Hautes-Pyrénées",
                    "Pyrénées-Orientales",
                    "Bas-Rhin",
                    "Haut-Rhin",
                    "Rhône",
                    "Haute-Saône",
                    "Saône-et-Loire",
                    "Sarthe",
                    "Savoie",
                    "Haute-Savoie",
                    "Paris",
                    "Seine-Maritime",
                    "Seine-et-Marne",
                    "Yvelines",
                    "Deux-Sèvres",
                    "Somme",
                    "Tarn",
                    "Tarn-et-Garonne",
                    "Var",
                    "Vaucluse",
                    "Vendée",
                    "Vienne",
                    "Haute-Vienne",
                    "Vosges",
                    "Yonne",
                    "Territoire-de-Belfort",
                    "Essonne",
                    "Hauts-de-Seine",
                    "Seine-Saint-Denis",
                    "Val-de-Marne",
                    "Val-d'Oise"
    );


    @FXML
    protected void initialize(){
        dep.setItems(options);
        mapView.setZoom(5.5);
        mapView.setCenter(46.603354, 1.888334);
    }

    @FXML
    protected void recherche(){

    }

}