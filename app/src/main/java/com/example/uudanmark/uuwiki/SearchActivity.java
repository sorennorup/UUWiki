package com.example.uudanmark.uuwiki;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SearchActivity extends ActionBarActivity {

    Button getTextButton;
    String var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText SearchText = (EditText) findViewById(R.id.searchBox);




         var = SearchText.getText().toString();

        addListenerOnButton(SearchText);

    }

    public void addListenerOnButton(final EditText textObj) {


        getTextButton = (Button) findViewById(R.id.searchButton);

        final Context context = this;


         // Send data to the new activity when button is pressed
        getTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String var = textObj.getText().toString();
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("myData", var);
                startActivity(intent);

            }
        });
    }
}