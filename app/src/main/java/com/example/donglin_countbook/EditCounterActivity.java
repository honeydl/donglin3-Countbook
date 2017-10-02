

/*
 * EditCounterActivity
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


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity enables users to select a counter and edit all fields except date
 * Show detail information about this counter3
 *
 * @author donglin
 * @version 1.0
 * @since 1,0
 */


public class EditCounterActivity extends AppCompatActivity {
    EditText nameText;
    EditText initText;
    EditText currentText;
    EditText dateText;
    EditText commentText;
    int initvalue;
    int currentvalue;
    String name;
    String comment;
    String initvalueString;
    String currentvalueString;
    Button saveButton;
    Button resetButton;
    Button incrementButton;
    Button decrementButton;
    Button deleteButton;
    Counter counterText;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        nameText = (EditText) findViewById(R.id.add_counter_name);
        initText = (EditText) findViewById(R.id.add_initial_value);
        currentText = (EditText)findViewById(R.id.add_current_value);
        dateText = (EditText) findViewById(R.id.add_date);
        dateText.setEnabled(false);
        commentText = (EditText)findViewById(R.id.add_comment);
        saveButton= (Button) findViewById(R.id.save);
        resetButton=(Button) findViewById(R.id.reset);
        incrementButton=(Button) findViewById(R.id.increment);
        decrementButton=(Button) findViewById(R.id.decrement);
        deleteButton=(Button) findViewById(R.id.delete);

        //get the selected counter
        counterText = getIntent().getParcelableExtra("OLDCOUNTER");

        // get information of this counter
        nameText.setText(counterText.getName());
        initText.setText(Integer.toString(counterText.getInitialValue()));
        currentText.setText(Integer.toString(counterText.getCurrentValue()));
        dateText.setText(counterText.getDateString());
        commentText.setText(counterText.getComment());

        //reset current value to inital value
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                initvalueString = initText.getText().toString();
                currentvalueString = currentText.getText().toString();
                initvalue = Integer.valueOf(initvalueString);
                currentvalue = Integer.valueOf(currentvalueString);

                currentvalue = initvalue;
                currentText.setText(Integer.toString(currentvalue));
            }
        });
         //increment current value by one
        incrementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentvalueString = currentText.getText().toString();
                currentvalue = Integer.valueOf(currentvalueString) + 1;
                currentText.setText(Integer.toString(currentvalue));
            }
        });
        //decrement current value bu one
        decrementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                currentvalueString = currentText.getText().toString();
                currentvalue = Integer.valueOf(currentvalueString) - 1;
                currentText.setText(Integer.toString(currentvalue));
            }
        });
        //delete the selected counter
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                InputOutputGson ioGson = new InputOutputGson(EditCounterActivity.this);
                ioGson.deleteFile(counterText);
                finish();
            }
        });
        //save all updated info , check format legal before click save
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                comment = commentText.getText().toString();
                initvalueString = initText.getText().toString();
                currentvalueString = currentText.getText().toString();

                if (initvalueString.isEmpty() || initvalueString.trim().isEmpty()) {
                    Toast.makeText(EditCounterActivity.this, "init value is required", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty() || name.trim().isEmpty()) {
                    Toast.makeText(EditCounterActivity.this, "name is required", Toast.LENGTH_SHORT).show();
                } else if (currentvalueString.isEmpty() || currentvalueString.trim().isEmpty()) {
                    Toast.makeText(EditCounterActivity.this, "current value is required", Toast.LENGTH_SHORT).show();
                } else if (!isStringInt(initvalueString) || !isStringInt(currentvalueString)) {
                    Toast.makeText(EditCounterActivity.this, "init value should be an integer", Toast.LENGTH_SHORT).show();
                } else if (!isStringInt(currentvalueString)) {
                    Toast.makeText(EditCounterActivity.this, "current value should be an integer", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(initvalueString) < 0) {
                    Toast.makeText(EditCounterActivity.this, "init value should be non-negative", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(currentvalueString) < 0) {
                    Toast.makeText(EditCounterActivity.this, "current value should be non-negative", Toast.LENGTH_SHORT).show();
                } else {
                    initvalue = Integer.valueOf(initvalueString);
                    currentvalue = Integer.valueOf(currentvalueString);

                    //delete the old counter
                    InputOutputGson ioGson = new InputOutputGson(EditCounterActivity.this);
                    ioGson.deleteFile(counterText);

                    // save a new counter into file with the new updated info
                    Counter newCounter = new Counter();
                    newCounter.setName(name);
                    newCounter.setInitialValue(initvalue);
                    newCounter.setCurrentValue(currentvalue);
                    newCounter.setComment(comment);
                    newCounter.setDate(counterText.getCounterDate());

                    //change date if current value is changed
                    if (currentvalue != counterText.getCurrentValue()) {
                        newCounter.setDate();
                    }
                    // save in fileusing gson
                    ioGson.saveInFile(newCounter);
                    finish();
                }
            }
        });
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
