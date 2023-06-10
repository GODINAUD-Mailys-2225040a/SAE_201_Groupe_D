package com.example.sae_201_groupe_d;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SeismeCSVReader
{
    private ArrayList<SeismeCSVLine> usablelist;
    private String csvName;

    public ArrayList<SeismeCSVLine> getUsablelist() {
        return usablelist;
    }

    public SeismeCSVReader(String csvName)
    {
        this.csvName = csvName;
        this.usablelist = new ArrayList<>();
        init(csvName);
    }

    private void init(String csvName)
    {
        try (Scanner file = new Scanner(new File(csvName))) {
            System.out.println("file ok");
            file.nextLine();
            while (file.hasNext()) {

                String nl = new String(file.nextLine());
                System.out.println(nl);
                SeismeCSVLine line = new SeismeCSVLine();
                line.getRecordFromLine(nl);

                usablelist.add(line);
            }
        } catch (Exception e) {System.out.println("file failed");}
    }

}
