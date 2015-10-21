package com.example.uudanmark.uuwiki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;


public class SecondActivity extends ActionBarActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        String result = intent.getStringExtra("myData");



        // connects to the database file sqlite
        DataBaseWrapper dataBaseWrapper = new DataBaseWrapper(this);


        try {
            dataBaseWrapper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "SELECT * from Forkortelser2 where Begreb like '"+result+"%' ";
        // creates a cusor object that get the values of the db
        Cursor cursor = dataBaseWrapper.getReadableDatabase().rawQuery(query,null);
                //getReadableDatabase().rawQuery(query,null);

        //This class put all the search results into an object and store it in an array
        ValuesInsertActivity ins = new ValuesInsertActivity(cursor);
        ValuesInsertModel[] val =  ins.setUpResults();




        textView.setText(val[0].beskrivelse+" n/ "+val[0].forkortelse);
    }



}
