package com.example.donglin_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadAllCounter();
        total = (TextView) findViewById(R.id.counter_total_number);
        total.setText("Total is : " + Integer.toString(counterList.size()));
        //add the total counter  number on the top of the main activity
        adapter = new ArrayAdapter<>(this, R.layout.counter_list_item, counterList);
        counterListView.setAdapter(adapter);
        //set adapter as a counterLit array adapter.
    }
    @Override
    protected void onResume() {
        super.onResume();
        counterList.clear();
        loadAllCounter();
        adapter.notifyDataSetChanged();
    }

    private void loadAllCounter() {
        InputOutputGson IOGson = new InputOutputGson(this);
        counterList = IOGson.loadFromAllFiles();
    }

}
