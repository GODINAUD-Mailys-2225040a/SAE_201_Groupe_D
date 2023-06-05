package com.example.sae_201_groupe_d;

import java.util.ArrayList;
import java.util.Date;

public class SeismeFilter extends ArrayList<String>
{
    private Integer id;
    private Date date;
    private Integer heure;
    private String pays;
    private String region;
    private Double intEpicentrale;
    private Double qualIntEpicentrale;
    private String choc;

    public SeismeFilter(Integer id, Date date, Integer heure, String pays, String region,
                        Double intEpicentrale, Double qualIntEpicentrale, String choc)
    {
        super();
        this.id = id;
        this.date = date;
        this.heure = heure;
        this.pays = pays;
        this.region = region;
        this.intEpicentrale = intEpicentrale;
        this.qualIntEpicentrale = qualIntEpicentrale;
        this.choc = choc;
    }

    public SeismeFilter(Integer id, Date date, String pays, String region,
                        Double intEpicentrale, Double qualIntEpicentrale, String choc)
    {
        super();
        this.id = id;
        this.date = date;
        this.pays = pays;
        this.region = region;
        this.intEpicentrale = intEpicentrale;
        this.qualIntEpicentrale = qualIntEpicentrale;
        this.choc = choc;
    }
}
