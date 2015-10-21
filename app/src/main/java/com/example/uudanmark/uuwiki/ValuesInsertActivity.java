package com.example.uudanmark.uuwiki;

import android.database.Cursor;

/**
 * Created by soren on 12/08/15.
 */
public class ValuesInsertActivity {
    public Cursor cur;
    public ValuesInsertModel values;
    private ValuesInsertModel[] theValues = new ValuesInsertModel[100];


    public ValuesInsertActivity(Cursor cur) {
        this.cur = cur;

    }

    public ValuesInsertModel[] setUpResults(){
        cur.moveToFirst();
        int i;
        for(i = 0; i < cur.getCount();i++) {
            String begreb = getValue(this.cur, "Begreb");
            String fuldBetegnelse = getValue(this.cur, "Fuldbetegnelse");
            String beskrivelse = getValue(this.cur,"Beskrivelse");
            String ogsKaldt = getValue(this.cur,"Ogsåkaldet");
            String erStattetAf = getValue(this.cur,"Erstattetaf");

            String link = this.cur.getString(this.cur.getColumnIndex("Link"));
            String laesMere = this.cur.getString(cur.getColumnIndex("Læsmere"));
            String erstatter = this.cur.getString(cur.getColumnIndex("Erstatter"));


            values = new ValuesInsertModel(begreb, fuldBetegnelse, beskrivelse, ogsKaldt, erStattetAf, link, laesMere, erstatter);
            this.theValues[i] = values;
            cur.moveToNext();

        }
            return theValues;


    }
    private String getValue(Cursor cur,String str){
        String stringValue = cur.getString(cur.getColumnIndex(str));
        return stringValue;

    }

}
