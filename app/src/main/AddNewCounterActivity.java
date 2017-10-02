/*
 * AddNewCounterActivity
 *
 * Version 1.0
 *
 * Oct 1, 2017
 *
 * Copyright (c)  Copyright 2017 donglin CMPUT301, University of Alberta, All Rights Reserved.
 *                * You may use distribut, or modify this code under terms and conditions of the ode of Student Behavior at University of Alberta
 *                * You may find a copy of the license in this project. Otherwise please contact : hdlyzz@163.com
 */

package com.example.donglin_countbook;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity enables users to add a new Counter
 *  Check user's input if it's legal
 *  saved in file after getting all info from new counter
 *  show in main screen
 *  @author donglin
 *  @verion 1.9
 *  @since 1.0
 */
public class AddNewCounterActivity extends AppCompatActivity {
    String name;
    String comment;
    int init;
    EditText nameNew;
    EditText initNew;
    EditText commentNew;
    Button createButton;
    Button cancelButton;
    String initvalueString;

    /**
     * onCreate() method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);
        nameNew = (EditText) findViewById(R.id.add_counter_name );
        initNew = (EditText) findViewById(R.id.add_initial_value);
        commentNew = (EditText) findViewById(R.id.add_comment);
        createButton = (Button) findViewById(R.id.create_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameNew.getText().toString();
                comment = commentNew.getText().toString();
                initvalueString = initNew.getText().toString();

                if (initvalueString.isEmpty() || initvalueString.trim().isEmpty()) {
                    Toast.makeText(AddNewCounterActivity.this, "Init value is required", Toast.LENGTH_SHORT).show();
                }

                else if (name.isEmpty() || name.trim().isEmpty()) {
                    Toast.makeText(AddNewCounterActivity.this, " Name is required", Toast.LENGTH_SHORT).show();
                }

                else if (!isStringInt(initvalueString)) {
                    Toast.makeText(AddNewCounterActivity.this, "Enter Integer number for initial value", Toast.LENGTH_SHORT).show();
                }

                else if (Integer.valueOf(initvalueString) < 0) {
                    Toast.makeText(AddNewCounterActivity.this, "Initial value should be non-negative", Toast.LENGTH_SHORT).show();
                }

                else{
                    addCounter(name, Integer.valueOf(initvalueString), comment);
                }
            }
        });
    }

    /**
     * add new counter and save in single file
     * @param name new counter name
     * @param initValue  new counter initvalue
     * @param comment new counter comment
     */
    protected void addCounter(String name, int initValue, String comment) {
        try {
            Counter newCounter = new Counter(name, initValue, comment);
            InputOutputGson IOGson = new InputOutputGson(this);
            IOGson.saveInFile(newCounter);
            // http://stackoverflow.com/questions/14848590/return-back-to-mainactivity-from-another-activity
            finish();
            Log.d("list_file", fileList().toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * check if it's integer or not
     * @param s user's input string
     * @return  boolean
     */
    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}