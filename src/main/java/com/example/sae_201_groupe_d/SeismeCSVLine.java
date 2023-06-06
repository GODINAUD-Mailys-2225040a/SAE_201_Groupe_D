package com.example.sae_201_groupe_d;

import com.gluonhq.maps.MapView;
import javafx.fxml.FXML;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class SeismeCSVLine //Challah ça marche, à tester
{
    private String csvName;
    @FXML
    private MapView mapView;

    private Integer id;
    private String date;
    private Integer heure;
    private String nom;
    private String regionEpicentrale;
    private String choc;
    private Double rgfX;
    private Double rgfY;
    private Double latitudeWGS84;
    private Double longitudeWGS84;
    private Double intEpicentrale;
    private String qualIntEpicentrale;

    private ArrayList<SeismeCSVLine> usableList;

    public SeismeCSVLine(String csvName)
    {
        //Constructeur complet
        super();
        this.csvName = csvName;

        init(csvName);
    }
    public SeismeCSVLine()
    {
        //Constructeur complet
        super();
    }

    private void init(String csvName)
    {
        try (Scanner scanner = new Scanner(csvName)) {
            while (scanner.hasNextLine()) {
                usableList.add(getRecordFromLine(scanner.nextLine()));
            }
        }
    }

    private SeismeCSVLine getRecordFromLine(String line) {
        SeismeCSVLine values = new SeismeCSVLine();

        ArrayList<Field> privateFields = new ArrayList<>();
        Field[] allFields = SeismeCSVLine.class.getDeclaredFields();
        for (Field field : allFields) {
            privateFields.add(field);
        }

        int cpt = 0;

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                if (rowScanner.toString() == "")
                    privateFields.set(cpt, null);
                else
                    switch (cpt)
                    {
                        case (1):
                            values.setId(Integer.parseInt(rowScanner.toString()));
                            break;
                        case (2):
                            values.setDate(rowScanner.toString());
                            break;
                        case (3):
                            values.setHeure(Integer.parseInt(rowScanner.toString()));
                            break;
                        case (4):
                            values.setNom(rowScanner.toString());
                            break;
                        case (5):
                            values.setRegionEpicentrale(rowScanner.toString());
                            break;
                        case (6):
                            values.setChoc(rowScanner.toString());
                            break;
                        case (7):
                            values.setRgfX(Double.parseDouble(rowScanner.toString()));
                            break;
                        case (8):
                            values.setRgfY(Double.parseDouble(rowScanner.toString()));
                            break;
                        case(9):
                            values.setLatitudeWGS84(Double.parseDouble(rowScanner.toString()));
                            break;
                        case(10):
                            values.setLongitudeWGS84(Double.parseDouble(rowScanner.toString()));
                            break;
                        case(11):
                            values.setIntEpicentrale(Double.parseDouble(rowScanner.toString()));
                            break;
                        case(12):
                            values.setQualIntEpicentrale(rowScanner.toString());
                            break;
                        default:
                            System.out.println("bruh");
                    }
            }
        }
        return values;
    }

    public void setId(Integer id) {this.id = id;}
    public void setDate(String date) {this.date = date;}
    public void setHeure(Integer heure) {this.heure = heure;}
    public void setNom(String nom) {this.nom = nom;}
    public void setRegionEpicentrale(String regionEpicentrale) {this.regionEpicentrale = regionEpicentrale;}
    public void setChoc(String choc) {this.choc = choc;}
    public void setRgfX(Double rgfX) {this.rgfX = rgfX;}
    public void setRgfY(Double rgfY) {this.rgfY = rgfY;}
    public void setLatitudeWGS84(Double latitudeWGS84) {this.latitudeWGS84 = latitudeWGS84;}
    public void setLongitudeWGS84(Double longitudeWGS84) {this.longitudeWGS84 = longitudeWGS84;}
    public void setIntEpicentrale(Double intEpicentrale) {this.intEpicentrale = intEpicentrale;}
    public void setQualIntEpicentrale(String qualIntEpicentrale) {this.qualIntEpicentrale = qualIntEpicentrale;}
    public ArrayList<SeismeCSVLine> getUsableList() {return usableList;}


    @Override
    public String toString() {
        return "SeismeFilter[" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", heure=" + heure +
                ", nom='" + nom + '\'' +
                ", regionEpicentrale='" + regionEpicentrale + '\'' +
                ", choc='" + choc + '\'' +
                ", rgfX=" + rgfX +
                ", rgfY=" + rgfY +
                ", latitudeWGS84=" + latitudeWGS84 +
                ", longitudeWGS84=" + longitudeWGS84 +
                ", intEpicentrale=" + intEpicentrale +
                ", qualIntEpicentrale='" + qualIntEpicentrale + '\'' +
                ", usableList=" + usableList +
                ']';
    }
}
