package com.example.sae_201_groupe_d;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import com.gluonhq.maps.MapView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;


public class SeismeController {

    @FXML
    private GridPane donnees;

    @FXML
    private MapView mapView;

    @FXML
    private ComboBox<String> dep;

    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    private SeismeCSVReader file;

    private ArrayList<SeismeCSVLine> listeFiltree;

    private ArrayList<String> listeDeFiltre;

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

        file = new SeismeCSVReader("src/main/resources/com/example/sae_201_groupe_d/SisFrance_seismes_20230605145730.csv");

        dep.setOnAction( actionEvent -> {
            listeFiltree = filtre();
            int col = 0;
            int row = 0;
            for (SeismeCSVLine line : listeFiltree)
            {
                Label label = new Label(line.toString());
                donnees.add(label, col, row);
                ++row;
            }
        });
    }

    @FXML
    protected void recherchecoordonnees(){
        mapView.setCenter(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()));
        mapView.setZoom(8);
    }

    @FXML
    protected void recherchedep(){
        listeDeFiltre.add("departement");
        String depSelecte = dep.getValue();
        double latitude = 0;
        double longitude = 0;
        if (depSelecte.equals("Ain")) {
            latitude = 46.2050;
            longitude = 5.2254;
        } else if (depSelecte.equals("Aisne")) {
            latitude = 49.4919;
            longitude = 3.5715;
        } else if (depSelecte.equals("Allier")) {
            latitude = 46.2070;
            longitude = 3.3400;
        } else if (depSelecte.equals("Alpes-de-Haute-Provence")) {
            latitude = 44.0926;
            longitude = 6.2352;
        } else if (depSelecte.equals("Hautes-Alpes")) {
            latitude = 44.6235;
            longitude = 6.4527;
        } else if (depSelecte.equals("Alpes-Maritimes")) {
            latitude = 43.9119;
            longitude = 7.0796;
        } else if (depSelecte.equals("Ardèche")) {
            latitude = 44.7350;
            longitude = 4.5990;
        } else if (depSelecte.equals("Ardennes")) {
            latitude = 49.5421;
            longitude = 4.7275;
        } else if (depSelecte.equals("Ariège")) {
            latitude = 42.9242;
            longitude = 1.6172;
        } else if (depSelecte.equals("Aube")) {
            latitude = 48.3616;
            longitude = 4.0742;
        } else if (depSelecte.equals("Aude")) {
            latitude = 43.2134;
            longitude = 2.3496;
        } else if (depSelecte.equals("Aveyron")) {
            latitude = 44.3494;
            longitude = 2.5727;
        } else if (depSelecte.equals("Bouches-du-Rhône")) {
            latitude = 43.5297;
            longitude = 5.4474;
        } else if (depSelecte.equals("Calvados")) {
            latitude = 49.0322;
            longitude = -0.1667;
        } else if (depSelecte.equals("Cantal")) {
            latitude = 45.0606;
            longitude = 2.9046;
        } else if (depSelecte.equals("Charente")) {
            latitude = 45.8415;
            longitude = -0.3870;
        } else if (depSelecte.equals("Charente-Maritime")) {
            latitude = 45.8900;
            longitude = -0.9721;
        } else if (depSelecte.equals("Cher")) {
            latitude = 47.0820;
            longitude = 2.3973;
        } else if (depSelecte.equals("Corrèze")) {
            latitude = 45.3378;
            longitude = 1.8301;
        } else if (depSelecte.equals("Côte-d'Or")) {
            latitude = 47.3230;
            longitude = 4.7821;
        } else if (depSelecte.equals("Côtes-d'Armor")) {
            latitude = 48.4914;
            longitude = -2.9228;
        } else if (depSelecte.equals("Creuse")) {
            latitude = 46.1662;
            longitude = 1.8883;
        } else if (depSelecte.equals("Dordogne")) {
            latitude = 45.0706;
            longitude = 0.7182;
        } else if (depSelecte.equals("Doubs")) {
            latitude = 47.2500;
            longitude = 6.0215;
        } else if (depSelecte.equals("Drôme")) {
            latitude = 44.6914;
            longitude = 5.0750;
        } else if (depSelecte.equals("Eure")) {
            latitude = 49.0540;
            longitude = 1.1501;
        } else if (depSelecte.equals("Eure-et-Loir")) {
            latitude = 48.4407;
            longitude = 1.5093;
        } else if (depSelecte.equals("Finistère")) {
            latitude = 48.2020;
            longitude = -4.3016;
        } else if (depSelecte.equals("Gard")) {
            latitude = 43.8367;
            longitude = 4.3601;
        } else if (depSelecte.equals("Haute-Garonne")) {
            latitude = 43.6047;
            longitude = 1.4442;
        } else if (depSelecte.equals("Gers")) {
            latitude = 43.7302;
            longitude = 0.6920;
        } else if (depSelecte.equals("Gironde")) {
            latitude = 44.6488;
            longitude = -0.4652;
        } else if (depSelecte.equals("Hérault")) {
            latitude = 43.5912;
            longitude = 3.2584;
        } else if (depSelecte.equals("Ille-et-Vilaine")) {
            latitude = 48.1147;
            longitude = -1.6849;
        } else if (depSelecte.equals("Indre")) {
            latitude = 46.8388;
            longitude = 1.7411;
        } else if (depSelecte.equals("Indre-et-Loire")) {
            latitude = 47.2197;
            longitude = 0.9687;
        } else if (depSelecte.equals("Isère")) {
            latitude = 45.2395;
            longitude = 5.5370;
        } else if (depSelecte.equals("Jura")) {
            latitude = 46.7209;
            longitude = 5.7910;
        } else if (depSelecte.equals("Landes")) {
            latitude = 43.9651;
            longitude = -0.6278;
        } else if (depSelecte.equals("Loir-et-Cher")) {
            latitude = 47.5685;
            longitude = 1.3306;
        } else if (depSelecte.equals("Loire")) {
            latitude = 45.7407;
            longitude = 4.5261;
        } else if (depSelecte.equals("Haute-Loire")) {
            latitude = 45.1287;
            longitude = 3.8333;
        } else if (depSelecte.equals("Loire-Atlantique")) {
            latitude = 47.2667;
            longitude = -1.4500;
        } else if (depSelecte.equals("Loiret")) {
            latitude = 47.9022;
            longitude = 2.1282;
        } else if (depSelecte.equals("Lot")) {
            latitude = 44.5980;
            longitude = 1.6896;
        } else if (depSelecte.equals("Lot-et-Garonne")) {
            latitude = 44.3155;
            longitude = 0.5169;
        } else if (depSelecte.equals("Lozère")) {
            latitude = 44.3847;
            longitude = 3.4931;
        } else if (depSelecte.equals("Maine-et-Loire")) {
            latitude = 47.4732;
            longitude = -0.5510;
        } else if (depSelecte.equals("Manche")) {
            latitude = 49.2416;
            longitude = -1.3536;
        } else if (depSelecte.equals("Marne")) {
            latitude = 49.0833;
            longitude = 4.0417;
        } else if (depSelecte.equals("Haute-Marne")) {
            latitude = 48.0667;
            longitude = 5.1500;
        } else if (depSelecte.equals("Mayenne")) {
            latitude = 48.3012;
            longitude = -0.6417;
        } else if (depSelecte.equals("Meurthe-et-Moselle")) {
            latitude = 48.6848;
            longitude = 6.1650;
        } else if (depSelecte.equals("Meuse")) {
            latitude = 49.1098;
            longitude = 5.2319;
        } else if (depSelecte.equals("Morbihan")) {
            latitude = 47.6556;
            longitude = -2.9530;
        } else if (depSelecte.equals("Moselle")) {
            latitude = 49.1199;
            longitude = 6.1765;
        } else if (depSelecte.equals("Nièvre")) {
            latitude = 46.9864;
            longitude = 3.1621;
        } else if (depSelecte.equals("Nord")) {
            latitude = 50.6292;
            longitude = 3.0573;
        } else if (depSelecte.equals("Oise")) {
            latitude = 49.3550;
            longitude = 2.7844;
        } else if (depSelecte.equals("Orne")) {
            latitude = 48.5907;
            longitude = -0.0061;
        } else if (depSelecte.equals("Pas-de-Calais")) {
            latitude = 50.5200;
            longitude = 2.6200;
        } else if (depSelecte.equals("Puy-de-Dôme")) {
            latitude = 45.7664;
            longitude = 3.1097;
        } else if (depSelecte.equals("Pyrénées-Atlantiques")) {
            latitude = 43.3219;
            longitude = -0.6838;
        } else if (depSelecte.equals("Hautes-Pyrénées")) {
            latitude = 43.0880;
            longitude = 0.0313;
        } else if (depSelecte.equals("Pyrénées-Orientales")) {
            latitude = 42.6110;
            longitude = 2.3447;
        } else if (depSelecte.equals("Bas-Rhin")) {
            latitude = 48.5833;
            longitude = 7.7500;
        } else if (depSelecte.equals("Haut-Rhin")) {
            latitude = 47.8960;
            longitude = 7.3549;
        } else if (depSelecte.equals("Rhône")) {
            latitude = 45.7578;
            longitude = 4.8320;
        } else if (depSelecte.equals("Haute-Saône")) {
            latitude = 47.6389;
            longitude = 6.1264;
        } else if (depSelecte.equals("Saône-et-Loire")) {
            latitude = 46.8267;
            longitude = 4.6007;
        } else if (depSelecte.equals("Sarthe")) {
            latitude = 47.9613;
            longitude = 0.2181;
        } else if (depSelecte.equals("Savoie")) {
            latitude = 45.5667;
            longitude = 6.3000;
        } else if (depSelecte.equals("Haute-Savoie")) {
            latitude = 46.0523;
            longitude = 6.7412;
        } else if (depSelecte.equals("Paris")) {
            latitude = 48.8566;
            longitude = 2.3522;
        } else if (depSelecte.equals("Seine-Maritime")) {
            latitude = 49.4431;
            longitude = 1.1034;
        } else if (depSelecte.equals("Seine-et-Marne")) {
            latitude = 48.5412;
            longitude = 2.6554;
        } else if (depSelecte.equals("Yvelines")) {
            latitude = 48.8155;
            longitude = 2.1959;
        } else if (depSelecte.equals("Deux-Sèvres")) {
            latitude = 46.3242;
            longitude = -0.4658;
        } else if (depSelecte.equals("Somme")) {
            latitude = 49.9017;
            longitude = 2.3022;
        } else if (depSelecte.equals("Tarn")) {
            latitude = 43.9309;
            longitude = 2.1469;
        } else if (depSelecte.equals("Tarn-et-Garonne")) {
            latitude = 44.0446;
            longitude = 1.3524;
        } else if (depSelecte.equals("Var")) {
            latitude = 43.5247;
            longitude = 6.9314;
        } else if (depSelecte.equals("Vaucluse")) {
            latitude = 43.9470;
            longitude = 4.8087;
        } else if (depSelecte.equals("Vendée")) {
            latitude = 46.6712;
            longitude = -1.4274;
        } else if (depSelecte.equals("Vienne")) {
            latitude = 46.5810;
            longitude = 0.3369;
        } else if (depSelecte.equals("Haute-Vienne")) {
            latitude = 45.8336;
            longitude = 1.2611;
        } else if (depSelecte.equals("Vosges")) {
            latitude = 48.1730;
            longitude = 6.4510;
        } else if (depSelecte.equals("Yonne")) {
            latitude = 47.7995;
            longitude = 3.5669;
        } else if (depSelecte.equals("Territoire-de-Belfort")) {
            latitude = 47.6391;
            longitude = 6.8628;
        } else if (depSelecte.equals("Essonne")) {
            latitude = 48.4980;
            longitude = 2.4634;
        } else if (depSelecte.equals("Hauts-de-Seine")) {
            latitude = 48.8285;
            longitude = 2.2137;
        } else if (depSelecte.equals("Seine-Saint-Denis")) {
            latitude = 48.9102;
            longitude = 2.4846;
        } else if (depSelecte.equals("Val-de-Marne")) {
            latitude = 48.7793;
            longitude = 2.491;
        }
        else if (depSelecte.equals("Val-d'Oise")) {
            latitude = 49.0728;
            longitude = 2.1510;
        }
        mapView.setZoom(10);
        mapView.setCenter(latitude, longitude);

    }

    private ArrayList<SeismeCSVLine> filtre()
    {
        ArrayList<SeismeCSVLine> liste = new ArrayList<>();
        boolean dejaFiltreeUneFois = false;

        for (String filtre : listeDeFiltre)
        {
            if(dejaFiltreeUneFois)
            {
                System.out.println("bruh");
            }
            else {

                switch (filtre)
                {
                    case ("departement"):
                        for (SeismeCSVLine ligne : file.getUsablelist())
                        {
                            if (ligne.getRegionEpicentrale() == dep.getValue())
                                liste.add(ligne);
                        }
                }
                dejaFiltreeUneFois = true;
            }
        }

        return liste;
    }
}