package com.example.donglin_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewCounterActivity extends AppCompatActivity {
    String name;
    String comment;
    int init;
    EditText nameNew;
    EditText initNew;
    EditText commentNew;
    Button createButton;
    String initString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);
        nameNew = (EditText) findViewById(R.id.add_counter_name );
        initNew = (EditText) findViewById(R.id.add_initial_value);
        commentNew = (EditText) findViewById(R.id.add_comment);
        createButton = (Button) findViewById(R.id.create_button);

        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name = nameNew.getText().toString();
                comment = commentNew.getText().toString();
                initString = initNew.getText().toString();

    }
}
