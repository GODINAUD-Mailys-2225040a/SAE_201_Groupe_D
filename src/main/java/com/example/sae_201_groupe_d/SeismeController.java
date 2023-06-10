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

     ObservableList<String> options = FXCollections.observableArrayList(
             "CHARENTES",
             "NIVERNAIS",
             "POITOU",
             "NORMANDIE",
             "FRANCHE-COMTE",
             "ANJOU",
             "DAUPHINE",
             "CEVENNES",
             "GUYENNE",
             "ALSACE",
             "BRETAGNE",
             "PYRENEES CENTRALES",
             "BERRY",
             "PYRENEES ORIENTALES",
             "PROVENCE",
             "PAYS NANTAIS ET VENDEEN",
             "AUVERGNE",
             "LORRAINE",
             "QUERCY-ROUERGUE",
             "ALPES MARITIMES",
             "ALPES PROVENCALES",
             "TOURAINE",
             "PYRENEES OCCIDENTALES",
             "LANGUEDOC",
             "VAUCLUSE",
             "FOREZ-LYONNAIS",
             "LIMOUSIN",
             "ALPES DAUPHINOISES",
             "CHAMPAGNE",
             "ILES ANGLO-NORMANDES",
             "GASCOGNE",
             "VOSGES",
             "BOURGOGNE",
             "ALPES SAVOYARDES",
             "PICARDIE",
             "BRESSE ET JURA BRESSAN",
             "CORSE",
             "FLANDRE-ARTOIS",
             "BOURBONNAIS",
             "MAINE",
             "ILE-DE-FRANCE",
             "ORLEANAIS-BLESOIS-PAYS CHARTRAIN",
             "ARDENNE"
    );


    @FXML
    protected void initialize(){
        dep.setItems(options);
        mapView.setZoom(5.5);
        mapView.setCenter(46.603354, 1.888334);

        file = new SeismeCSVReader("src/main/resources/com/example/sae_201_groupe_d/SisFrance_seismes_20230605145730.csv");

    }

    @FXML
    protected void recherchecoordonnees(){
        mapView.setCenter(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()));
        mapView.setZoom(8);
    }

    @FXML
    protected void recherchedep(){
        String depSelecte = dep.getValue();
        double latitude = 0;
        double longitude = 0;
        if (depSelecte.equals("CHARENTES")) {
            latitude = 46.2050;
            longitude = 5.2254;
        } else if (depSelecte.equals("NIVERNAIS")) {
            latitude = 49.4919;
            longitude = 3.5715;
        } else if (depSelecte.equals("POITOU")) {
            latitude = 46.2070;
            longitude = 3.3400;
        } else if (depSelecte.equals("NORMANDIE")) {
            latitude = 44.0926;
            longitude = 6.2352;
        } else if (depSelecte.equals("FRANCHE-COMPTE")) {
            latitude = 44.6235;
            longitude = 6.4527;
        } else if (depSelecte.equals("ANJOU")) {
            latitude = 43.9119;
            longitude = 7.0796;
        } else if (depSelecte.equals("DAUPHINE")) {
            latitude = 44.7350;
            longitude = 4.5990;
        } else if (depSelecte.equals("CEVENNES")) {
            latitude = 49.5421;
            longitude = 4.7275;
        } else if (depSelecte.equals("")) {
            latitude = 42.9242;
            longitude = 1.6172;
        } else if (depSelecte.equals("")) {
            latitude = 48.3616;
            longitude = 4.0742;
        } else if (depSelecte.equals("")) {
            latitude = 43.2134;
            longitude = 2.3496;
        } else if (depSelecte.equals("")) {
            latitude = 44.3494;
            longitude = 2.5727;
        }
        else if (depSelecte.equals("")) {
            latitude = 43.5297;
            longitude = 5.4474;
        } else if (depSelecte.equals("")) {
            latitude = 49.0322;
            longitude = -0.1667;
        } else if (depSelecte.equals("")) {
            latitude = 45.0606;
            longitude = 2.9046;
        } else if (depSelecte.equals("")) {
            latitude = 45.8415;
            longitude = -0.3870;
        } else if (depSelecte.equals("")) {
            latitude = 45.8900;
            longitude = -0.9721;
        } else if (depSelecte.equals("")) {
            latitude = 47.0820;
            longitude = 2.3973;
        } else if (depSelecte.equals("")) {
            latitude = 45.3378;
            longitude = 1.8301;
        } else if (depSelecte.equals("")) {
            latitude = 47.3230;
            longitude = 4.7821;
        } else if (depSelecte.equals("")) {
            latitude = 48.4914;
            longitude = -2.9228;
        } else if (depSelecte.equals("")) {
            latitude = 46.1662;
            longitude = 1.8883;
        } else if (depSelecte.equals("")) {
            latitude = 45.0706;
            longitude = 0.7182;
        } else if (depSelecte.equals("")) {
            latitude = 47.2500;
            longitude = 6.0215;
        } else if (depSelecte.equals("")) {
            latitude = 44.6914;
            longitude = 5.0750;
        } else if (depSelecte.equals("")) {
            latitude = 49.0540;
            longitude = 1.1501;
        } else if (depSelecte.equals("")) {
            latitude = 48.4407;
            longitude = 1.5093;
        } else if (depSelecte.equals("")) {
            latitude = 48.2020;
            longitude = -4.3016;
        } else if (depSelecte.equals("")) {
            latitude = 43.8367;
            longitude = 4.3601;
        } else if (depSelecte.equals("")) {
            latitude = 43.6047;
            longitude = 1.4442;
        } else if (depSelecte.equals("")) {
            latitude = 43.7302;
            longitude = 0.6920;
        } else if (depSelecte.equals("")) {
            latitude = 44.6488;
            longitude = -0.4652;
        } else if (depSelecte.equals("")) {
            latitude = 43.5912;
            longitude = 3.2584;
        } else if (depSelecte.equals("")) {
            latitude = 48.1147;
            longitude = -1.6849;
        } else if (depSelecte.equals("")) {
            latitude = 46.8388;
            longitude = 1.7411;
        } else if (depSelecte.equals("")) {
            latitude = 47.2197;
            longitude = 0.9687;
        } else if (depSelecte.equals("")) {
            latitude = 45.2395;
            longitude = 5.5370;
        } else if (depSelecte.equals("")) {
            latitude = 46.7209;
            longitude = 5.7910;
        } else if (depSelecte.equals("")) {
            latitude = 43.9651;
            longitude = -0.6278;
        } else if (depSelecte.equals("")) {
            latitude = 47.5685;
            longitude = 1.3306;
        } else if (depSelecte.equals("")) {
            latitude = 45.7407;
            longitude = 4.5261;
        } else if (depSelecte.equals("")) {
            latitude = 45.1287;
            longitude = 3.8333;
        } else if (depSelecte.equals("")) {
            latitude = 47.2667;
            longitude = -1.4500;
        } else if (depSelecte.equals("")) {
            latitude = 47.9022;
            longitude = 2.1282;
        } else if (depSelecte.equals("")) {
            latitude = 44.5980;
            longitude = 1.6896;
        } else if (depSelecte.equals("")) {
            latitude = 44.3155;
            longitude = 0.5169;
        } else if (depSelecte.equals("")) {
            latitude = 44.3847;
            longitude = 3.4931;
        } else if (depSelecte.equals("")) {
            latitude = 47.4732;
            longitude = -0.5510;
        } else if (depSelecte.equals("")) {
            latitude = 49.2416;
            longitude = -1.3536;
        } else if (depSelecte.equals("")) {
            latitude = 49.0833;
            longitude = 4.0417;
        } else if (depSelecte.equals("")) {
            latitude = 48.0667;
            longitude = 5.1500;
        } else if (depSelecte.equals("")) {
            latitude = 48.3012;
            longitude = -0.6417;
        } else if (depSelecte.equals("")) {
            latitude = 48.6848;
            longitude = 6.1650;
        } else if (depSelecte.equals("")) {
            latitude = 49.1098;
            longitude = 5.2319;
        } else if (depSelecte.equals("")) {
            latitude = 47.6556;
            longitude = -2.9530;
        } else if (depSelecte.equals("")) {
            latitude = 49.1199;
            longitude = 6.1765;
        } else if (depSelecte.equals("")) {
            latitude = 46.9864;
            longitude = 3.1621;
        } else if (depSelecte.equals("")) {
            latitude = 50.6292;
            longitude = 3.0573;
        } else if (depSelecte.equals("")) {
            latitude = 49.3550;
            longitude = 2.7844;
        } else if (depSelecte.equals("")) {
            latitude = 48.5907;
            longitude = -0.0061;
        } else if (depSelecte.equals("")) {
            latitude = 50.5200;
            longitude = 2.6200;
        } else if (depSelecte.equals("")) {
            latitude = 45.7664;
            longitude = 3.1097;
        } else if (depSelecte.equals("")) {
            latitude = 43.3219;
            longitude = -0.6838;
        } else if (depSelecte.equals("")) {
            latitude = 43.0880;
            longitude = 0.0313;
        } else if (depSelecte.equals("")) {
            latitude = 42.6110;
            longitude = 2.3447;
        } else if (depSelecte.equals("")) {
            latitude = 48.5833;
            longitude = 7.7500;
        } else if (depSelecte.equals("")) {
            latitude = 47.8960;
            longitude = 7.3549;
        } else if (depSelecte.equals("")) {
            latitude = 45.7578;
            longitude = 4.8320;
        } else if (depSelecte.equals("")) {
            latitude = 47.6389;
            longitude = 6.1264;
        } else if (depSelecte.equals("")) {
            latitude = 46.8267;
            longitude = 4.6007;
        } else if (depSelecte.equals("")) {
            latitude = 47.9613;
            longitude = 0.2181;
        } else if (depSelecte.equals("")) {
            latitude = 45.5667;
            longitude = 6.3000;
        } else if (depSelecte.equals("")) {
            latitude = 46.0523;
            longitude = 6.7412;
        } else if (depSelecte.equals("")) {
            latitude = 48.8566;
            longitude = 2.3522;
        } else if (depSelecte.equals("")) {
            latitude = 49.4431;
            longitude = 1.1034;
        } else if (depSelecte.equals("")) {
            latitude = 48.5412;
            longitude = 2.6554;
        } else if (depSelecte.equals("")) {
            latitude = 48.8155;
            longitude = 2.1959;
        } else if (depSelecte.equals("")) {
            latitude = 46.3242;
            longitude = -0.4658;
        } else if (depSelecte.equals("")) {
            latitude = 49.9017;
            longitude = 2.3022;
        } else if (depSelecte.equals("")) {
            latitude = 43.9309;
            longitude = 2.1469;
        } else if (depSelecte.equals("")) {
            latitude = 44.0446;
            longitude = 1.3524;
        } else if (depSelecte.equals("")) {
            latitude = 43.5247;
            longitude = 6.9314;
        } else if (depSelecte.equals("")) {
            latitude = 43.9470;
            longitude = 4.8087;
        } else if (depSelecte.equals("")) {
            latitude = 46.6712;
            longitude = -1.4274;
        } else if (depSelecte.equals("")) {
            latitude = 46.5810;
            longitude = 0.3369;
        } else if (depSelecte.equals("")) {
            latitude = 45.8336;
            longitude = 1.2611;
        } else if (depSelecte.equals("")) {
            latitude = 48.1730;
            longitude = 6.4510;
        } else if (depSelecte.equals("")) {
            latitude = 47.7995;
            longitude = 3.5669;
        } else if (depSelecte.equals("")) {
            latitude = 47.6391;
            longitude = 6.8628;
        } else if (depSelecte.equals("")) {
            latitude = 48.4980;
            longitude = 2.4634;
        } else if (depSelecte.equals("")) {
            latitude = 48.8285;
            longitude = 2.2137;
        } else if (depSelecte.equals("")) {
            latitude = 48.9102;
            longitude = 2.4846;
        } else if (depSelecte.equals("")) {
            latitude = 48.7793;
            longitude = 2.491;
        }
        else if (depSelecte.equals("")) {
            latitude = 49.0728;
            longitude = 2.1510;
        }
        mapView.setZoom(10);
        mapView.setCenter(latitude, longitude);

    }
}