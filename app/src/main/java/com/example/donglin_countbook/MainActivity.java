/*
 * Mainactivity
 *
 * Version 1.0
 *
 * Oct 1 2017
 *
 * Copyright (c)  Copyright 2017 donglin CMPUT301, University of Alberta, All Rights Reserved.
 *                * You may use distribut, or modify this code under terms and conditions of the ode of Student Behavior at University of Alberta
 *                * You may find a copy of the license in this project. Otherwise please contact : hdlyzz@163.com
 */

package com.example.donglin_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/**
 *This class is the main page
 * this activity contains the Counters list and add button
 * Add button enables users to add a new counter
 * the counter summary has counter's name , counter's date  and counter's current value
 * @author  donglin
 * @version 1.0
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {
    private TextView total;
    private String name;
    private String comment;
    private int initialValue;
    private Counter newCounter;
    private ArrayList<Counter> counterList = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;
    private ListView counterListView;
    private Button addButton;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add button, and jump to the new page for adding a nre counter
        addButton=(Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, AddNewCounterActivity.class);
                startActivity(intentAdd);
            }
        });

        //create list view for counterLists
        counterListView = (ListView) findViewById(R.id.counter_list);
        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * when click, get the information of the old counter
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //jump to EditCounterActivity for editing
                Intent intentEdit = new Intent(MainActivity.this, EditCounterActivity.class);
                Counter oldCounter = (Counter)(adapterView.getItemAtPosition(i));
                intentEdit.putExtra("OLDCOUNTER", oldCounter);
                startActivity(intentEdit);
            }
        });

    }

    // onStart method
    @Override
    protected void onStart() {
        super.onStart();
        loadAllCounter();
        //count counters and show in screen
        total = (TextView) findViewById(R.id.counter_total_number);
        total.setText("Total is : " + Integer.toString(counterList.size()));
        adapter = new ArrayAdapter<>(this, R.layout.counter_list_item, counterList);
        counterListView.setAdapter(adapter);
    }
    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        loadAllCounter();
        adapter.notifyDataSetChanged();
    }

    /**
     * Load all counters by Gson
     */
    private void loadAllCounter() {
        InputOutputGson IOGson = new InputOutputGson(this);
        ArrayList<Counter> counters = IOGson.loadFromAllFiles();
        counterList.clear();
        if(!counters.isEmpty()){
            for(Counter counter: counters){
                counterList.add(counter);
            }
        }
    }

}
