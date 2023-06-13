package com.example.sae_201_groupe_d;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;


public class SeismeController {

    @FXML
    private GridPane tab;

    @FXML
    private MapView mapView;

    @FXML
    private ComboBox<String> reg;

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

    //Liste des régions présentes dans le fichier csv
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
    protected void initialize()
    {
        /*
        Fonction d'initialisation de l'application à son lancement
         */
        file = new SeismeCSVReader("src/main/resources/com/example/sae_201_groupe_d/SisFrance_seismes_20230605145730.csv");

        reg.setItems(options);
        mapView.setZoom(5.5);
        mapView.setCenter(46.603354, 1.888334);

        listeFiltre = new ArrayList<>();
        constructGrid();
    }

    @FXML
    private void stats()
    {
        /*
        Fonction correspondante au bouton permettant
        d'afficher différentes statistiques par rapport
        aux séismes séléctionnés par des filtres.
         */
        contenubase = contenu.getChildren().get(0);
        contenu.getChildren().clear();

        BorderPane newContent = new BorderPane();

        GridPane stats = new GridPane();

        Label text = new Label("Statistiques liées au tableau de données : ");
        newContent.setTop(text);

        ArrayList<String> listeAnnees = new ArrayList<>();
        for (int i=1100 ; i < 2100 ; i += 100)
        {
            listeAnnees.add(String.valueOf(i));
        }

        int cpt = 0;
        ArrayList<Integer> listeNbSeismeParAn = new ArrayList<>(Collections.nCopies(10, 0));
        for (SeismeCSVLine line : file.getUsableList())
        {
            for (int i=1100 ; i < 2100 ; i += 100)
            {
                if (Integer.parseInt(line.getDate().substring(0,4)) > i)
                {
                    ++cpt;
                }
                else
                {
                    listeNbSeismeParAn.set(cpt, listeNbSeismeParAn.get(cpt) + 1);
                    break;
                }
            }
            cpt = 0;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Évolution du nombre d'enregistrements");
        for (int i=0 ; i < listeAnnees.size() ; ++i)
        {
            series.getData().add(new XYChart.Data(listeAnnees.get(i) , listeNbSeismeParAn.get(i)));
        }

        ObservableList<String> oListeAnnees = FXCollections.observableArrayList(listeAnnees);

        CategoryAxis x = new CategoryAxis(oListeAnnees);
        NumberAxis y = new NumberAxis();

        x.setLabel("Années");
        y.setLabel("Nombre de séismes");

        LineChart<String, Number> lineChart = new LineChart<>(x, y);
        lineChart.setTitle("Evolution du nombre de séismes enregistrés dans le temps");
        lineChart.getData().add(series);

        stats.add(lineChart, 0,0);

        newContent.setCenter(stats);

        contenu.getChildren().add(newContent);
    }

    @FXML
    private void donnees()
    {
        /*
        Fonction correspondant au bouton
        permettant d'afficher les données des
        séismes voulus.
         */
        contenu.getChildren().clear();
        contenu.getChildren().add(contenubase);
    }

    @FXML
    protected void marqueurs()
    {
        /*
        --NON FONCTIONNELLE--
        Fonction utilisée à la fin d'une application
        de filtre et permet de placer un point sur la
        carte correspondant à chaques séismes présents
        dans la liste affichée courante.
         */
        double latitude = 0;
        double longitude = 0;
        for (int i = 1 ; i < tab.getRowCount() ; ++i){
            Node node = getNodeByRowColumnIndex(i,8, tab);

            if (node instanceof TextField) {
                TextField textField1 = (TextField) node;
                latitude = Double.parseDouble(textField1.getText());
            }

            Node node2 = getNodeByRowColumnIndex(i, 9, tab);
            if (node2 instanceof TextField) {
                TextField textField2 = (TextField) node;
                longitude = Double.parseDouble(textField2.getText());
            }
            MapPoint marqueur = new MapPoint(latitude, longitude);
            MapLayer newLayer = new CustomCircleMarkerLayer(marqueur);
            mapView.addLayer(newLayer);
        }
    }
    //Fonction utile à marqueurs() qui permettait une boucle dans le tableau
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
    protected void recherchecoordonnees()
    {
        /*
        Fonction permettant de centrer la carte
        sur un point spécifié par ses coordonnées
        latitudinales ainsi que longitudinales.
         */
        mapView.setCenter(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()));
        mapView.setZoom(8);
    }

    @FXML
    protected void recherchereg()
    {
        /*
        Fonction permettant d'appliquer un filtre
        de région par rapport a la région
        séléctionnée. La carte sera mise au dessus
        de la région en question à l'aide de ses coordonnées.
         */
        String regSelecte = reg.getValue();
        double latitude = 0;
        double longitude = 0;
        if (regSelecte.equals("CHARENTES")) {
            latitude = 45.7;
            longitude = 0.5;
        } else if (regSelecte.equals("NIVERNAIS")) {
            latitude = 47;
            longitude = 3.5;
        } else if (regSelecte.equals("POITOU")) {
            latitude = 46.6;
            longitude = 0;
        } else if (regSelecte.equals("NORMANDIE")) {
            latitude = 49;
            longitude = -0.5;
        } else if (regSelecte.equals("FRANCHE-COMTE")) {
            latitude = 47;
            longitude = 6.3;
        } else if (regSelecte.equals("ANJOU")) {
            latitude = 47.4;
            longitude = -0.25;
        } else if (regSelecte.equals("DAUPHINE")) {
            latitude = 44.9;
            longitude = 5.7;
        } else if (regSelecte.equals("CEVENNES")) {
            latitude = 44.35;
            longitude = 3.75;
        } else if (regSelecte.equals("GUYENNE")) {
            latitude = 45.2;
            longitude = 0;
        } else if (regSelecte.equals("ALSACE")) {
            latitude = 47.75;
            longitude = 7.5;
        } else if (regSelecte.equals("BRETAGNE")) {
            latitude = 48.5;
            longitude = -3.3;
        } else if (regSelecte.equals("PYRENEES CENTRALES")) {
            latitude = 43.1;
            longitude = 0.9;
        } else if (regSelecte.equals("BERRY")) {
            latitude = 46.9;
            longitude = 2;
        } else if (regSelecte.equals("PYRENEES ORIENTALES")) {
            latitude = 42.5;
            longitude = 2.75;
        } else if (regSelecte.equals("PROVENCE")) {
            latitude = 44;
            longitude = 5.75;
        } else if (regSelecte.equals("PAYS NANTAIS ET VENDEEN")) {
            latitude = 47.25;
            longitude = -1;
        } else if (regSelecte.equals("AUVERGNE")) {
            latitude = 45.7;
            longitude = 3;
        } else if (regSelecte.equals("LORRAINE")) {
            latitude = 49;
            longitude = 6.2;
        } else if (regSelecte.equals("QUERCY-ROUERGUE")) {
            latitude = 44.4;
            longitude = 2;
        } else if (regSelecte.equals("ALPES MARITIMES")) {
            latitude = 43.9;
            longitude = 7;
        } else if (regSelecte.equals("ALPES PROVENCALES")) {
            latitude = 44.3;
            longitude = 6.5;
        } else if (regSelecte.equals("TOURAINE")) {
            latitude = 47.3;
            longitude = 1;
        } else if (regSelecte.equals("PYRENEES OCCIDENTALES")) {
            latitude = 43.1;
            longitude = 0;
        } else if (regSelecte.equals("LANGUEDOC")) {
            latitude = 43.5;
            longitude = 3.2;
        } else if (regSelecte.equals("VAUCLUSE")) {
            latitude = 44;
            longitude = 5.2;
        } else if (regSelecte.equals("FOREZ-LYONNAIS")) {
            latitude = 45.75;
            longitude = 4;
        } else if (regSelecte.equals("LIMOUSIN")) {
            latitude = 45.65;
            longitude = 2;
        } else if (regSelecte.equals("ALPES DAUPHINOISES")) {
            latitude = 45.2;
            longitude = 6;
        } else if (regSelecte.equals("CHAMPAGNE")) {
            latitude = 48.9;
            longitude = 4;
        } else if (regSelecte.equals("ILES ANGLO-NORMANDES")) {
            latitude = 49.6;
            longitude = -1.5;
        } else if (regSelecte.equals("GASCOGNE")) {
            latitude = 43.6;
            longitude = 0.5;
        } else if (regSelecte.equals("VOSGES")) {
            latitude = 48;
            longitude = 6.5;
        } else if (regSelecte.equals("BOURGOGNE")) {
            latitude = 47;
            longitude = 4;
        } else if (regSelecte.equals("ALPES SAVOYARDES")) {
            latitude = 46;
            longitude = 6.5;
        } else if (regSelecte.equals("PICARDIE")) {
            latitude = 49.65;
            longitude = 3;
        } else if (regSelecte.equals("BRESSE ET JURA BRESSAN")) {
            latitude = 46.55;
            longitude = 5.7;
        } else if (regSelecte.equals("CORSE")) {
            latitude = 42.5;
            longitude = 9;
        } else if (regSelecte.equals("FLANDRE-ARTOIS")) {
            latitude = 50.75;
            longitude = 3;
        } else if (regSelecte.equals("BOURBONNAIS")) {
            latitude = 46.6;
            longitude = 3.4;
        } else if (regSelecte.equals("MAINE")) {
            latitude = 48.15;
            longitude = 0;
        } else if (regSelecte.equals("ILE-DE-FRANCE")) {
            latitude = 48.7;
            longitude = 2.7;
        } else if (regSelecte.equals("ORLEANAIS-BLESOIS-PAYS CHARTRAIN")) {
            latitude = 47.9;
            longitude = 1.8;
        } else if (regSelecte.equals("ARDENNE")) {
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
        /*
        Fonction appliquant un filtre par rapport
        à la date des séismes étudiés.
        Si les zones de texte sont vides, une valeur
        par défaut est utilisée pour encadrer du mieux
        possible les dates voulues.
        Fonctionnement similaire au filtre d'intensité.
         */
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
        /*
        Fonction appliquant un filtre par rapport
        à l'intensité des séismes.
        Si les zones de texte sont vides, une valeur
        par défaut est utilisée pour encadrer du mieux
        possible les intensités voulues.
        Fonctionnement similaire au filtre de date.
         */
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
        /*
        Fonction appliquant un filtre pour trouver
        tout les séismes dans un rayon donné en km
        autour d'un point spécifié avec les latitudes
        et longitudes.
         */

        listeFiltre.add("alentours");
        filtrer(listeFiltre);
        constructGrid();
    }

    protected void filtrer (ArrayList<String> listeFiltre)
    {
        /*
        Fonction de filtre utilisée par toutes les autres
        fonction appliquant des filtres.
        Réinitialise l'attribut usableList de file pour repartir
        d'une liste non modifiée, puis, en fonction des filtres
        voulant être appliqués (indiqués par listeFiltre).
        La fonction boucle sur cette dernière liste énnoncée
        et utilise un switch statement pour déterminer
        comment filtrer au fur et a mesure du parcours de la
        liste.
        Le filtre agis sur une liste de SeismeCSVLine appelée
        toRemove, stocke toutes les lignes à enlever en fonction
        des filtres appliqués, puis les enlève tous de l'attribut
        usableList de file.
        La fonction fini par afficher la localisation des lignes
        gardées sur la carte.
         */
        file.reinit();
        ArrayList<SeismeCSVLine> toRemove = new ArrayList<>();

        for (String filtre : listeFiltre)
        {
            switch (filtre)
            {
                case("region") :
                    for (SeismeCSVLine line : file.getUsableList())
                    {
                        /*
                        En cas de filtre par région, si la région de
                        la ligne courante n'est pas la même que celle
                        appliquée par le filtre, elle sera supprimée de
                        la liste.
                         */
                        if (!(line.getRegionEpicentrale().equals(reg.getValue())))
                            toRemove.add(line);
                    }
                    break;
                case("date") :
                    for (SeismeCSVLine line : file.getUsableList())
                    {
                        /*
                        En cas de filtre par date, si la date de la ligne
                        courrante n'est pas comprise entre les deux bornes
                        correspondantes, elle sera supprimée de la liste.
                         */
                        if (Integer.parseInt(line.getDate().substring(0, 4)) < dateBorneMin ||
                                Integer.parseInt(line.getDate().substring(0, 4)) > dateBorneMax)
                            toRemove.add(line);
                    }
                    break;
                case("intensite") :
                    for (SeismeCSVLine line : file.getUsableList())
                    {
                        /*
                        En cas de filtre par intensité, si l'intensité
                        de la ligne courrante n'est pas comprise entre
                        les deux bornes correspondantes, elle sera supprimée
                        de la liste.
                         */
                        if (line.getIntEpicentrale() < intBorneMin ||
                                line.getIntEpicentrale() > intBorneMax)
                            toRemove.add(line);
                    }
                case("alentours") :
                    for (SeismeCSVLine line : file.getUsableList())
                    {
                        /*
                        En cas de filtre par alentours, si une des coordonnées
                        de la ligne courante est nulle ou bien la distance en
                        km entre le point central saisi dans le filtre et les
                        coordonnées de la ligne courante est plus grande que
                        le rayon en km appliqué par le filtre d'alentours, la
                        ligne sera supprimée.
                         */
                        if (line.getLatitudeWGS84() == null || line.getLongitudeWGS84() == null ||
                                calculateDistance(Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()),
                                        line.getLatitudeWGS84(), line.getLongitudeWGS84()) > Double.parseDouble(rayon.getText()))
                        {
                            toRemove.add(line);
                        }
                    }
            }
        }
        //marqueurs();
        file.removeLines(toRemove);
    }

    @FXML
    protected void removeFilters()
    {
        /*
        Fonction utilisée par le bouton permettant d'enlever
        la totalité des filtres.
        Vide la liste de filtre à appliquer, pour filtrer
        l'attribut usableListe de file à partir de rien,
        aucune modification ne sera donc apportée à la
        liste des séismes. Elle recentre ensuite la carte.
         */
        listeFiltre.clear();
        filtrer(listeFiltre);
        constructGrid();
        mapView.setZoom(5.5);
        mapView.setCenter(46.603354, 1.888334);
    }

    protected void constructGrid()
    {
        /*
        Fonction utilisée dans tout type d'affinage de recherche.
        Permet de reconstruire le tableau d'affichage des données
        des séismes dans l'application. Elle parcourt l'attribut
        usableList de la variable file et place les bons attributs
        dans les bonnes colonnes.
         */
        tab.getChildren().clear();
        tab.getChildren().addAll(id, date, h, nom, RE, ch, X, Y, lati, longi, IE, QE);
        int row = 1;
        int col = -1;
        for (SeismeCSVLine line : file.getUsableList()) {
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


    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        /*
        Fonction utilisée lors du filtre d'alentours.
        La formule utilisée est la formule Harvesine et permet de calculer
        la distance entre deux points donnés en latitude et longitude WGS84.
         */
        final double R = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;
        return distance;
    }
}