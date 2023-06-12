package com.example.sae_201_groupe_d;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import com.gluonhq.maps.MapView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    @FXML
    private TextField dateBorneSup;

    @FXML
    private TextField dateBorneInf;
    private int dateBorneMax, dateBorneMin;

    @FXML
    private TextField intBorneSup;

    @FXML
    private TextField intBorneInf;
    private double intBorneMax, intBorneMin;

    private SeismeCSVReader file;

    private ArrayList<String> listeFiltre;

    @FXML
    private Label id, date, h, nom, RE, ch, X, Y, lati, longi, IE, QE;

    @FXML
    private Pane contenu;

    @FXML
    private TextField rayon;

    private Node contenubase;

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
        //isAsc = true;

        listeFiltre = new ArrayList<>();
        constructGrid();
    }

    @FXML
    private void stats(ActionEvent event){

        contenubase = contenu.getChildren().get(0);
        contenu.getChildren().clear();

        BorderPane newContent = new BorderPane();

        Label text = new Label("Statistiques liées au tableau de données : ");
        newContent.setTop(text);



        contenu.getChildren().add(newContent);
    }

    @FXML
    private void donnees(ActionEvent event){
        contenu.getChildren().clear();
        contenu.getChildren().add(contenubase);
    }

    @FXML
    protected void marqueurs(){
        double latitude = 0;
        double longitude = 0;
        for (int i = 1 ; i < tab.getRowCount() ; ++i){
            Node node = getNodeByRowColumnIndex(i,8, tab);

            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                latitude = Double.parseDouble(textField.getText());
            }

            Node node2 = getNodeByRowColumnIndex(i, 9, tab);
            if (node2 instanceof TextField) {
                TextField textField = (TextField) node;
                longitude = Double.parseDouble(textField.getText());
            }
            MapPoint marqueur = new MapPoint(latitude, longitude);
            MapLayer newLayer = new CustomCircleMarkerLayer(marqueur);
            mapView.addLayer(newLayer);
        }
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane tab) {
        Node result = null;
        ObservableList<Node> childrens = tab.getChildren();

        for (Node node : childrens) {
            if(tab.getRowIndex(node) == row && tab.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
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
        } else if (depSelecte.equals("FRANCHE-COMTE")) {
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
        } else if (depSelecte.equals("BERRY")) {
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

    @FXML
    protected void filtrerDate ()
    {
        if (dateBorneSup.getText().isEmpty()) {dateBorneMax = 2222;}
        else {dateBorneMax = Integer.parseInt(dateBorneSup.getText());}

        if (dateBorneInf.getText().isEmpty()) {dateBorneMin = 0;}
        else {dateBorneMin = Integer.parseInt(dateBorneInf.getText());}

        listeFiltre.add("date");
        filtrer(listeFiltre);
        constructGrid();
    }

    @FXML
    protected void filtrerIntensite ()
    {
        if (intBorneSup.getText().isEmpty()) {intBorneMax = 10.0;}
        else {intBorneMax = Double.parseDouble(intBorneSup.getText());}

        if (intBorneInf.getText().isEmpty()) {intBorneMin = 0.0;}
        else {intBorneMin = Double.parseDouble(intBorneInf.getText());}

        listeFiltre.add("intensite");
        filtrer(listeFiltre);
        constructGrid();
    }

    @FXML
    protected void filtrerAlentours ()
    {

        listeFiltre.add("alentours");
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
                    break;
                case("date") :
                    for (SeismeCSVLine line : file.getUsablelist())
                    {
                        if (Integer.parseInt(line.getDate().substring(0, 4)) < dateBorneMin ||
                                Integer.parseInt(line.getDate().substring(0, 4)) > dateBorneMax)
                            toRemove.add(line);
                    }
                    break;
                case("intensite") :
                    for (SeismeCSVLine line : file.getUsablelist())
                    {
                        if (line.getIntEpicentrale() < intBorneMin ||
                                line.getIntEpicentrale() > intBorneMax)
                            toRemove.add(line);
                    }
                case("alentours") :
                    for (SeismeCSVLine line : file.getUsablelist())
                    {

                        if (line.getLatitudeWGS84() == null || line.getLongitudeWGS84() == null)
                        {
                            toRemove.add(line);
                        }
                        else if (distance(Double.parseDouble(lat.getText()), Double.parseDouble(lat.getText()),
                                line.getLatitudeWGS84(), line.getLongitudeWGS84()) > Double.parseDouble(rayon.getText()))
                        {
                            toRemove.add(line);
                        }
                        else {
                            System.out.println(distance(Double.parseDouble(lat.getText()), Double.parseDouble(lat.getText()),
                                    line.getLatitudeWGS84(), line.getLongitudeWGS84()));
                        }
                    }
            }
        }
        marqueurs();
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
                        if (line.getId() == null) {label.setText("");}
                        else label.setText(line.getId().toString());
                        col ++;
                        break;
                    case (2):
                        if (line.getDate() == null) {label.setText("");}
                        else label.setText(line.getDate());
                        col ++;
                        break;
                    case (3):
                        if (line.getHeure() == null) {label.setText("");}
                        else {label.setText(line.getHeure());}
                        col ++;
                        break;
                    case (4):
                        if (line.getNom() == null) {label.setText("");}
                        else label.setText(line.getNom());
                        col ++;
                        break;
                    case (5):
                        if (line.getRegionEpicentrale() == null) {label.setText("");}
                        else label.setText(line.getRegionEpicentrale());
                        col ++;
                        break;
                    case (6):
                        if (line.getChoc() == null) {label.setText("");}
                        else label.setText(line.getChoc());
                        col ++;
                        break;
                    case (7):
                        if (line.getRgfX() == null) {label.setText("");}
                        else label.setText(line.getRgfX().toString());
                        col ++;
                        break;
                    case (8):
                        if (line.getRgfY() == null) {label.setText("");}
                        else label.setText(line.getRgfY().toString());
                        col ++;
                        break;
                    case (9):
                        if (line.getLatitudeWGS84() == null) {label.setText("");}
                        else label.setText(line.getLatitudeWGS84().toString());
                        col ++;
                        break;
                    case (10):
                        if (line.getLongitudeWGS84() == null) {label.setText("");}
                        else label.setText(line.getLongitudeWGS84().toString());
                        col ++;
                        break;
                    case (11):
                        if (line.getIntEpicentrale() == null) {label.setText("");}
                        else label.setText(line.getIntEpicentrale().toString());
                        col ++;
                        break;
                    case (12):
                        if (line.getQualIntEpicentrale() == null) {label.setText("");}
                        else label.setText(line.getQualIntEpicentrale());
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


    private double distanceKmLatLon (double lat2, double lon2, double lat1, double lon1)
    {
        //formule de Harvesine
        final double R = 6371; //Rayon de la Terre

        Double latDistance = degreEnRad(lat2-lat1);
        Double lonDistance = degreEnRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(degreEnRad(lat1)) * Math.cos(degreEnRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;

        return distance;
    }
    private double degreEnRad(double deg)
    {
        return deg * (Math.PI/180);
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(degreEnRad(lat1)) * Math.sin(degreEnRad(lat2)) + Math.cos(degreEnRad(lat1)) *
                Math.cos(degreEnRad(lat2)) * Math.cos(degreEnRad(theta));
        dist = Math.acos(dist);
        dist = degreEnRad(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

}