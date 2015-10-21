package com.example.uudanmark.uuwiki;

/**
 * Created by soren on 12/08/15.
 */
public class ValuesInsertModel {
    public String forkortelse;
    public String fuld_Betegnelse;
    public String beskrivelse;
    public String ogsaaKaldet;
    public String erstattet_af;
    public String linket;
    public String laesMere;
    public String erstatter;



    public ValuesInsertModel(String forkortelse, String fuld_Betegnelse, String beskrivelse, String ogsaaKaldet, String erstattet_af, String linket, String laesMere, String erstatter) {
        this.forkortelse = forkortelse;
        this.fuld_Betegnelse = fuld_Betegnelse;
        this.beskrivelse = beskrivelse;
        this.ogsaaKaldet = ogsaaKaldet;
        this.erstattet_af = erstattet_af;
        this.linket = linket;
        this.laesMere = laesMere;
        this.erstatter = erstatter;

    }
}
