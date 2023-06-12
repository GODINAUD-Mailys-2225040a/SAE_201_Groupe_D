package com.example.sae_201_groupe_d;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class SeismeCSVLine
{
    private Integer id;
    private String date;
    private String heure;
    private String nom;
    private String regionEpicentrale;
    private String choc;
    private Double rgfX;
    private Double rgfY;
    private Double latitudeWGS84;
    private Double longitudeWGS84;
    private Double intEpicentrale;
    private String qualIntEpicentrale;

    public SeismeCSVLine()
    {
        super();
    }

    public void getRecordFromLine(String line) {

        ArrayList<Field> privateFields = new ArrayList<>();
        Field[] allFields = SeismeCSVLine.class.getDeclaredFields();
        for (Field field : allFields) {
            privateFields.add(field);
        }

        int cpt = 0;

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                ++cpt;
                String current = new String(rowScanner.next());
                if (current.isEmpty()) {
                    privateFields.set(cpt, null);
                }
                else {
                    switch (cpt)
                    {
                        case (1):
                            this.id = (Integer.parseInt(current));
                            break;
                        case (2):
                            this.date = (current);
                            break;
                        case (3):
                            this.heure = (current);
                            break;
                        case (4):
                            this.nom = (current);
                            break;
                        case (5):
                            this.regionEpicentrale = (current);
                            break;
                        case (6):
                            this.choc = (current);
                            break;
                        case (7):
                            this.rgfX = (Double.parseDouble(current));
                            break;
                        case (8):
                            this.rgfY = (Double.parseDouble(current));
                            break;
                        case(9):
                            this.latitudeWGS84 = (Double.parseDouble(current));
                            break;
                        case(10):
                            this.longitudeWGS84 = (Double.parseDouble(current));
                            break;
                        case(11):
                            this.intEpicentrale = (Double.parseDouble(current));
                            break;
                        case(12):
                            this.qualIntEpicentrale = (current);
                            break;
                        default:
                            System.out.println("fail " + cpt);
                    }
                    if (cpt >= 12) {cpt = 0;}
                }
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getNom() {
        return nom;
    }

    public String getRegionEpicentrale() {
        return regionEpicentrale;
    }

    public String getChoc() {
        return choc;
    }

    public Double getRgfX() {
        return rgfX;
    }

    public Double getRgfY() {
        return rgfY;
    }

    public Double getLatitudeWGS84() {
        return latitudeWGS84;
    }

    public Double getLongitudeWGS84() {
        return longitudeWGS84;
    }

    public Double getIntEpicentrale() {
        return intEpicentrale;
    }

    public String getQualIntEpicentrale() {
        return qualIntEpicentrale;
    }


    @Override
    public String toString() {
        return "SeismeCSVLine{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", nom='" + nom + '\'' +
                ", regionEpicentrale='" + regionEpicentrale + '\'' +
                ", choc='" + choc + '\'' +
                ", rgfX=" + rgfX +
                ", rgfY=" + rgfY +
                ", latitudeWGS84=" + latitudeWGS84 +
                ", longitudeWGS84=" + longitudeWGS84 +
                ", intEpicentrale=" + intEpicentrale +
                ", qualIntEpicentrale='" + qualIntEpicentrale + '\'' +
                '}';
    }
}
