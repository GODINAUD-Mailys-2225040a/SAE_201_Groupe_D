package com.example.sae_201_groupe_d;

import java.io.File;
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
            file.nextLine();
            while (file.hasNext()) {
                String nl = (file.nextLine());
                SeismeCSVLine line = new SeismeCSVLine();
                line.getRecordFromLine(nl);

                usablelist.add(line);
            }
        } catch (Exception e) {System.out.println(e);}
    }

    public void reinit()
    {
        usablelist.removeAll(usablelist);
        init(csvName);
    }

    public void removeLines (ArrayList<SeismeCSVLine> toRemove)
    {
        usablelist.removeAll(toRemove);
    }

}
