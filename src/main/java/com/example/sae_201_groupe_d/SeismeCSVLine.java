package com.example.sae_201_groupe_d;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class SeismeCSVLine
{
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

    public SeismeCSVLine()
    {
        //Constructeur complet
        super();
    }

    /*
    private void init(String csvName)
    {
        try (FileInputStream file = new FileInputStream(csvName)) {
            System.out.println("file ok");
            //while (file.()) {
            //    usableList.add(getRecordFromLine(file.nextLine()));
            //}
        } catch (Exception failed) {System.out.println("file failed");}
    }
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
                System.out.println(current);
                if (current.isEmpty())
                    privateFields.set(cpt, null);
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
                            this.heure = (Integer.parseInt(current));
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
                }
            }
        }
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
                ']';
    }
}
