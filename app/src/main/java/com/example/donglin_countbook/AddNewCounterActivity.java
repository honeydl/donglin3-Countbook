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
    String initvalueString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);
        nameNew = (EditText) findViewById(R.id.add_counter_name );
        initNew = (EditText) findViewById(R.id.add_initial_value);
        commentNew = (EditText) findViewById(R.id.add_comment);
        createButton = (Button) findViewById(R.id.create_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameNew.getText().toString();
                comment = commentNew.getText().toString();
                initvalueString = initNew.getText().toString();

                if (initvalueString.matches("")) {
                    Toast.makeText(AddNewCounterActivity.this, "Init value is required", Toast.LENGTH_SHORT).show();
                }

                else if (name.matches("")) {
                    Toast.makeText(AddNewCounterActivity.this, " Name is required", Toast.LENGTH_SHORT).show();
                }

                else if (!isStringInt(initvalueString)) {
                    Toast.makeText(AddNewCounterActivity.this, "Enter Integer number ", Toast.LENGTH_SHORT).show();
                }

                else if (Integer.valueOf(initvalueString) < 0) {
                    Toast.makeText(AddNewCounterActivity.this, "Number should be non-negative", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent();
                    intent.putExtra("Name", name);
                    intent.putExtra("Initial value", Integer.valueOf(initvalueString));
                    intent.putExtra("Comment", comment);
                    finish();

                }
            }
        });
    }
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