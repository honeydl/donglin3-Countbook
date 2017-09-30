package com.example.donglin_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private TextView total;
    private ListView counterListView;
    private String name;
    private String comment;
    private int initialValue;
    private Counter newCounter;
    private ArrayList<Counter> counterList = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterListView = (ListView) findViewById(R.id.counters_list); //Create listView reference counterList Which contains all of the counters that user has created;
        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO may need to intent to an new activity that only enables users to increment, decrement, delete, and reset
                Intent intentEdit = new Intent(MainActivity.this, EditCounterActivity.class);
                startActivity(intentEdit);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(MainActivity.this, AddNewCounterActivity.class);
                startActivity(intentAdd);

            }
        });
    }

}
