package com.example.sae_201_groupe_d;

import java.io.FileInputStream;
import java.util.ArrayList;

public class SeismeCSVReader
{
    private ArrayList<SeismeCSVLine> usablelist;
    private String csvName;

    public SeismeCSVReader(String csvName)
    {
        this.csvName = csvName;
        this.usablelist = new ArrayList<>();
        init(csvName);
    }

    private void init(String csvName)
    {
        try (FileInputStream file = new FileInputStream(csvName)) {
            System.out.println("file ok");
            //while (file.) {
            //    usableList.add(getRecordFromLine(file.nextLine()));
            //}
        } catch (Exception failed) {System.out.println("file failed");}
    }
}
