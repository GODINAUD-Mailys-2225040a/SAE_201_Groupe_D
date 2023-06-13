package com.example.sae_201_groupe_d;

import java.io.File;
import java.util.*;

/**
 * Classe permettant de stocker et manipuler les différentes lignes
 * du fichier CSV.
 * Son attribut usableList correspond à la liste des lignes du fichier
 * sous forme de SeismeCSVLine.
 */
public class SeismeCSVReader {
    private ArrayList<SeismeCSVLine> usableList;
    private String csvName;

    /**
     * Constructeur de la classe SeismeCSVReader.
     *
     * @param csvName Le nom du fichier CSV.
     */
    public SeismeCSVReader(String csvName) {
        this.csvName = csvName;
        this.usableList = new ArrayList<>();
        init(csvName);
    }

    private void init(String csvName) {
        /**
         * Parcours le fichier CSV ligne par ligne tant que le fichier
         * possède une ligne à la suite à l'aide d'un Scanner,
         * convertit la ligne choisie en String à analyser et stocke ses
         * attributs par une nouvelle variable du type SeismeCSVLine qui
         * va donc être ajoutée à la usableList.
         */
        try (Scanner file = new Scanner(new File(csvName))) {
            file.nextLine();
            while (file.hasNext()) {
                String nl = (file.nextLine());
                SeismeCSVLine line = new SeismeCSVLine();
                line.getRecordFromLine(nl);

                usableList.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Réinitialise la liste des lignes utilisables en la vidant
     * et en la remplissant avec toutes les lignes non filtrées du fichier CSV.
     */
    public void reinit() {
        usableList.removeAll(usableList);
        init(csvName);
    }

    /**
     * Supprime les lignes spécifiées de la liste des lignes utilisables.
     *
     * @param toRemove La liste des lignes à supprimer.
     */
    public void removeLines(ArrayList<SeismeCSVLine> toRemove) {
        usableList.removeAll(toRemove);
    }

    /**
     * Getter pour la liste des lignes utilisables.
     *
     * @return La liste des lignes utilisables.
     */
    public ArrayList<SeismeCSVLine> getUsableList() {
        return usableList;
    }
}
