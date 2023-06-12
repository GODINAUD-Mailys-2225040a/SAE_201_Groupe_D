package com.example.sae_201_groupe_d;

import com.gluonhq.maps.MapPoint;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.util.ArrayList;


public class SeismeController {

    @FXML
    private GridPane tab;

    @FXML
    private MapView mapView;

    @FXML
    private ComboBox<String> dep;

    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    private SeismeCSVReader file;

    private ArrayList<String> listeFiltre;

    @FXML
    private Label id, date, h, nom, RE, ch, X, Y, lati, longi, IE, QE;



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
        file = new SeismeCSVReader("src/main/resources/com/example/sae_201_groupe_d/SisFrance_seismes_20230605145730.csv");
        dep.setItems(options);
        mapView.setZoom(5.5);
        mapView.setCenter(46.603354, 1.888334);
        listeFiltre = new ArrayList<>();
        constructGrid();
    }

    @FXML
    protected void marqueurs(){
        for (int i = 1 ; i < tab.getRowCount() ; ++i){
            for (int j = 0; j < tab.getColumnCount(); ++j){

            }

        }
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
            latitude = 45.7;
            longitude = 0.5;
        } else if (depSelecte.equals("NIVERNAIS")) {
            latitude = 47;
            longitude = 3.5;
        } else if (depSelecte.equals("POITOU")) {
            latitude = 46.6;
            longitude = 0;
        } else if (depSelecte.equals("NORMANDIE")) {
            latitude = 49;
            longitude = -0.5;
        } else if (depSelecte.equals("FRANCHE-COMPTE")) {
            latitude = 47;
            longitude = 6.3;
        } else if (depSelecte.equals("ANJOU")) {
            latitude = 47.4;
            longitude = -0.25;
        } else if (depSelecte.equals("DAUPHINE")) {
            latitude = 44.9;
            longitude = 5.7;
        } else if (depSelecte.equals("CEVENNES")) {
            latitude = 44.35;
            longitude = 3.75;
        } else if (depSelecte.equals("GUYENNE")) {
            latitude = 45.2;
            longitude = 0;
        } else if (depSelecte.equals("ALSACE")) {
            latitude = 47.75;
            longitude = 7.5;
        } else if (depSelecte.equals("BRETAGNE")) {
            latitude = 48.5;
            longitude = -3.3;
        } else if (depSelecte.equals("PYRENEES CENTRALES")) {
            latitude = 43.1;
            longitude = 0.9;
        }
        else if (depSelecte.equals("BERRY")) {
            latitude = 46.9;
            longitude = 2;
        } else if (depSelecte.equals("PYRENEES ORIENTALES")) {
            latitude = 42.5;
            longitude = 2.75;
        } else if (depSelecte.equals("PROVENCE")) {
            latitude = 44;
            longitude = 5.75;
        } else if (depSelecte.equals("PAYS NANTAIS ET VENDEEN")) {
            latitude = 47.25;
            longitude = -1;
        } else if (depSelecte.equals("AUVERGNE")) {
            latitude = 45.7;
            longitude = 3;
        } else if (depSelecte.equals("LORRAINE")) {
            latitude = 49;
            longitude = 6.2;
        } else if (depSelecte.equals("QUERCY-ROUERGUE")) {
            latitude = 44.4;
            longitude = 2;
        } else if (depSelecte.equals("ALPES MARITIMES")) {
            latitude = 43.9;
            longitude = 7;
        } else if (depSelecte.equals("ALPES PROVENCALES")) {
            latitude = 44.3;
            longitude = 6.5;
        } else if (depSelecte.equals("TOURAINE")) {
            latitude = 47.3;
            longitude = 1;
        } else if (depSelecte.equals("PYRENEES OCCIDENTALES")) {
            latitude = 43.1;
            longitude = 0;
        } else if (depSelecte.equals("LANGUEDOC")) {
            latitude = 43.5;
            longitude = 3.2;
        } else if (depSelecte.equals("VAUCLUSE")) {
            latitude = 44;
            longitude = 5.2;
        } else if (depSelecte.equals("FOREZ-LYONNAIS")) {
            latitude = 45.75;
            longitude = 4;
        } else if (depSelecte.equals("LIMOUSIN")) {
            latitude = 45.65;
            longitude = 2;
        } else if (depSelecte.equals("ALPES DAUPHINOISES")) {
            latitude = 45.2;
            longitude = 6;
        } else if (depSelecte.equals("CHAMPAGNE")) {
            latitude = 48.9;
            longitude = 4;
        } else if (depSelecte.equals("ILES ANGLO-NORMANDES")) {
            latitude = 49.6;
            longitude = -1.5;
        } else if (depSelecte.equals("GASCOGNE")) {
            latitude = 43.6;
            longitude = 0.5;
        } else if (depSelecte.equals("VOSGES")) {
            latitude = 48;
            longitude = 6.5;
        } else if (depSelecte.equals("BOURGOGNE")) {
            latitude = 47;
            longitude = 4;
        } else if (depSelecte.equals("ALPES SAVOYARDES")) {
            latitude = 46;
            longitude = 6.5;
        } else if (depSelecte.equals("PICARDIE")) {
            latitude = 49.65;
            longitude = 3;
        } else if (depSelecte.equals("BRESSE ET JURA BRESSAN")) {
            latitude = 46.55;
            longitude = 5.7;
        } else if (depSelecte.equals("CORSE")) {
            latitude = 42.5;
            longitude = 9;
        } else if (depSelecte.equals("FLANDRE-ARTOIS")) {
            latitude = 50.75;
            longitude = 3;
        } else if (depSelecte.equals("BOURBONNAIS")) {
            latitude = 46.6;
            longitude = 3.4;
        } else if (depSelecte.equals("MAINE")) {
            latitude = 48.15;
            longitude = 0;
        } else if (depSelecte.equals("ILE-DE-FRANCE")) {
            latitude = 48.7;
            longitude = 2.7;
        } else if (depSelecte.equals("ORLEANAIS-BLESOIS-PAYS CHARTRAIN")) {
            latitude = 47.9;
            longitude = 1.8;
        } else if (depSelecte.equals("ARDENNE")) {
            latitude = 49.9;
            longitude = -4.5;
        }
        mapView.setZoom(8);
        mapView.setCenter(latitude, longitude);

        listeFiltre.add("region");
        filtrer(listeFiltre);
        constructGrid();
    }

    protected void filtrer (ArrayList<String> listeFiltree)
    {
        file.reinit();

        ArrayList<SeismeCSVLine> toRemove = new ArrayList<>();

        for (String filtre : listeFiltree)
        {
            switch (filtre)
            {
                case("region") :
                    for (SeismeCSVLine line : file.getUsablelist())
                    {
                        if (!(line.getRegionEpicentrale().equals(dep.getValue())))
                            toRemove.add(line);
                    }
            }
        }
        file.removeLines(toRemove);
    }

    protected void constructGrid()
    {
        tab.getChildren().clear();
        tab.getChildren().addAll(id, date, h, nom, RE, ch, X, Y, lati, longi, IE, QE);
        int row = 1;
        int col = -1;
        for (SeismeCSVLine line : file.getUsablelist()) {
            for (int i = 1 ; i < 13 ; ++i) {
                Label label = new Label();
                switch (i) {
                    case (1):
                        label.setText(line.getId().toString());
                        col ++;
                        break;
                    case (2):
                        label.setText(line.getDate());
                        col ++;
                        break;
                    case (3):
                        if (line.getHeure() == null){
                            label.setText("");
                        }
                        else {
                            label.setText(line.getHeure());
                        }
                        col ++;
                        break;
                    case (4):
                        label.setText(line.getNom());
                        col ++;
                        break;
                    case (5):
                        label.setText(line.getRegionEpicentrale());;
                        col ++;
                        break;
                    case (6):
                        if (line.getChoc() == null){
                            label.setText("");
                        }
                        else label.setText(line.getChoc());
                        col ++;
                        break;
                    case (7):
                        if (line.getRgfX() == null){
                            label.setText("");
                        }
                        else label.setText(line.getRgfX().toString());
                        col ++;
                        break;
                    case (8):
                        if (line.getRgfY() == null){
                            label.setText("");
                        }
                        else label.setText(line.getRgfY().toString());
                        col ++;
                        break;
                    case (9):
                        if (line.getLongitudeWGS84() == null){
                            label.setText("");
                        }
                        else label.setText(line.getLongitudeWGS84().toString());
                        col ++;
                        break;
                    case (10):
                        if (line.getLatitudeWGS84() == null){
                            label.setText("");
                        } else label.setText(line.getLatitudeWGS84().toString());
                        col ++;
                        break;
                    case (11):
                        label.setText(line.getIntEpicentrale().toString());
                        col ++;
                        break;
                    case (12):
                        label.setText(line.getQualIntEpicentrale());
                        col ++;
                        break;
                    default:
                        System.out.println("fail ");
                }
                tab.add(label, col, row);
            }
            ++row;
            col = -1;
        }
    }
}