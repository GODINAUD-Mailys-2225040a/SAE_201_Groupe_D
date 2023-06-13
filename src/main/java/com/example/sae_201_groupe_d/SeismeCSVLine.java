package com.example.sae_201_groupe_d;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de stocker une ligne du fichier CSV pris en compte dans ce projet.
 * Ses attributs correspondent aux différentes données indiquées par le fichier utilisé et exploité.
 */
public class SeismeCSVLine {
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

    public SeismeCSVLine() {
        super();
    }

    /**
     * Fonction permettant de lire une ligne du fichier CSV et attribuer en conséquence les bonnes valeurs aux bons attributs.
     *
     * @param line La ligne du fichier CSV à lire.
     */
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
                } else {
                    switch (cpt) {
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
                        case (9):
                            this.latitudeWGS84 = (Double.parseDouble(current));
                            break;
                        case (10):
                            this.longitudeWGS84 = (Double.parseDouble(current));
                            break;
                        case (11):
                            this.intEpicentrale = (Double.parseDouble(current));
                            break;
                        case (12):
                            this.qualIntEpicentrale = (current);
                            cpt = 0;
                            break;
                        default:
                            System.out.println("fail " + cpt);
                    }
                }
            }
        }
    }

    /**
     * Getter for the id attribute.
     *
     * @return The value of the id attribute.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the date attribute.
     *
     * @return The value of the date attribute.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the heure attribute.
     *
     * @return The value of the heure attribute.
     */
    public String getHeure() {
        return heure;
    }

    /**
     * Getter for the nom attribute.
     *
     * @return The value of the nom attribute.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter for the regionEpicentrale attribute.
     *
     * @return The value of the regionEpicentrale attribute.
     */
    public String getRegionEpicentrale() {
        return regionEpicentrale;
    }

    /**
     * Getter for the choc attribute.
     *
     * @return The value of the choc attribute.
     */
    public String getChoc() {
        return choc;
    }

    /**
     * Getter for the rgfX attribute.
     *
     * @return The value of the rgfX attribute.
     */
    public Double getRgfX() {
        return rgfX;
    }

    /**
     * Getter for the rgfY attribute.
     *
     * @return The value of the rgfY attribute.
     */
    public Double getRgfY() {
        return rgfY;
    }

    /**
     * Getter for the latitudeWGS84 attribute.
     *
     * @return The value of the latitudeWGS84 attribute.
     */
    public Double getLatitudeWGS84() {
        return latitudeWGS84;
    }

    /**
     * Getter for the longitudeWGS84 attribute.
     *
     * @return The value of the longitudeWGS84 attribute.
     */
    public Double getLongitudeWGS84() {
        return longitudeWGS84;
    }

    /**
     * Getter for the intEpicentrale attribute.
     *
     * @return The value of the intEpicentrale attribute.
     */
    public Double getIntEpicentrale() {
        return intEpicentrale;
    }

    /**
     * Getter for the qualIntEpicentrale attribute.
     *
     * @return The value of the qualIntEpicentrale attribute.
     */
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
