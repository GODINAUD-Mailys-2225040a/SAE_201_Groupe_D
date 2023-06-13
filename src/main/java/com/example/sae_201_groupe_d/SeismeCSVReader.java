package com.example.sae_201_groupe_d;

import java.io.File;
import java.util.*;

public class SeismeCSVReader
    /*
    Classe permettant de stocker et manipuler les différentes lignes
    du fichier CSV.
    Son attribut usableList correspond à la liste des lignes du fichier
    sous forme de SeismeCSVLine.
     */
{
    private ArrayList<SeismeCSVLine> usableList;
    private String csvName;

    public SeismeCSVReader(String csvName)
    {
        this.csvName = csvName;
        this.usableList = new ArrayList<>();
        init(csvName);
    }

    private void init(String csvName)
    {
        /*
        Parcours le fichier CSV ligne par ligne tant que le fichier
        possède une ligne à la suite à l'aide d'un Scanner,
        converti la ligne choisie en String à analyser et stocker ses
        attribiuts par une nouvelle variable du type SeismeCSVLine qui
        va donc être ajoutée à la usableList.
         */
        try (Scanner file = new Scanner(new File(csvName))) {
            file.nextLine();
            while (file.hasNext()) {
                String nl = (file.nextLine());
                SeismeCSVLine line = new SeismeCSVLine();
                line.getRecordFromLine(nl);

                usableList.add(line);
            }
        } catch (Exception e) {System.out.println(e);}
    }

    public void reinit()
    {
        /*
        Utilisé lors de l'application de filtre.
        Vide la usableList pour la réinitialiser à son état de base
        (Comportant toutes les lignes non filtrées du fichier CSV).
         */
        usableList.removeAll(usableList);
        init(csvName);
    }

    public void removeLines (ArrayList<SeismeCSVLine> toRemove)
    {
        /*
        Utilisé lors de l'application de filtre.
        prend en argument une liste de SeismeCSVLine à enlever et
        les supprime de la liste.
         */
        usableList.removeAll(toRemove);
    }

    /*
    Getter
     */
    public ArrayList<SeismeCSVLine> getUsableList() {return usableList;}
}
