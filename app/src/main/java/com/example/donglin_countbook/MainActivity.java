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
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import  com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        InputOutputGson ioGson = new InputOutputGson(this);
        counterList = ioGson.loadFromAllFile();
        //load the data from the file
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
        InputOutputGson ioGson = new InputOutputGson(this);
        counterList = ioGson.loadFromAllFile();
        adapter.notifyDataSetChanged();

    }

//    private void loadFromFile() {
//        try {
//            FileInputStream fis = openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//
//            Gson gson = new Gson();
//            Type listType = new TypeToken<ArrayList<Counter>>() {
//            }.getType();
//            counterList = gson.fromJson(in, listType);
//
//        } catch (FileNotFoundException e) {
//            counterList = new ArrayList<Counter>();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }

//    }
//
//    /**
//     * save all the changes into a file
//     * <br>
//     *  load countersList from the file
//     */
//    private void saveInFile() {
//        try {
//            FileOutputStream fos = openFileOutput(FILENAME,
//                    Context.MODE_PRIVATE);
//
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
//
//            Gson gson = new Gson();
//            gson.toJson(counterList, out);
//
//            out.flush();
//
//            fos.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//    }

}
