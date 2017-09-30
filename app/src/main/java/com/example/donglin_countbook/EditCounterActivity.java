package com.example.donglin_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



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
    Counter CounterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        nameText = (EditText) findViewById(R.id.add_counter_name);
        initText = (EditText) findViewById(R.id.add_initial_value);
        currentText = (EditText)findViewById(R.id.add_current_value);
        dateText = (EditText) findViewById(R.id.add_date);
        commentText = (EditText)findViewById(R.id.add_comment);
        dateText.setEnabled(false);
        saveButton= (Button) findViewById(R.id.add);
        resetButton=(Button) findViewById(R.id.reset);
        incrementButton=(Button) findViewById(R.id.increment);
        decrementButton=(Button) findViewById(R.id.decrement);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name = nameText.getText().toString();
                comment = commentText.getText().toString();
                initvalueString = initText.getText().toString();
                currentvalueString = currentText.getText().toString();


                if (initvalueString.isEmpty() || initvalueString.trim().isEmpty()){
                    Toast.makeText(EditCounterActivity.this, "init value is required", Toast.LENGTH_SHORT).show();
                }
                else if (name.isEmpty() || name.trim().isEmpty()){
                    Toast.makeText(EditCounterActivity.this, "name is required", Toast.LENGTH_SHORT).show();
                }
                else if (currentvalueString.isEmpty() || currentvalueString.trim().isEmpty()){
                    Toast.makeText(EditCounterActivity.this, "current value is required", Toast.LENGTH_SHORT).show();
                }
                else if (!isStringInt(initvalueString) || !isStringInt(currentvalueString)){
                    Toast.makeText(EditCounterActivity.this, "init value should be an integer", Toast.LENGTH_SHORT).show();
                }
                else if (!isStringInt(currentvalueString)){
                    Toast.makeText(EditCounterActivity.this, "current value should be an integer", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.valueOf(initvalueString)<0||Integer.valueOf(currentvalueString)<0) {
                    Toast.makeText(EditCounterActivity.this, "init value should be non-negative", Toast.LENGTH_SHORT).show();
                }
                else if (Integer.valueOf(currentvalueString)<0) {
                    Toast.makeText(EditCounterActivity.this, "current value should be non-negative", Toast.LENGTH_SHORT).show();
                }
                //Question#1
//                else if (Integer.valueOf(initString)>Integer.valueOf(currentString)){
//                    Toast.makeText(edit_page.this, "current value must bigger than or equal to the init value!", Toast.LENGTH_SHORT).show();
//                }
                else {
                    initvalue = Integer.valueOf(initvalueString);
                    currentvalue = Integer.valueOf(currentvalueString);
                    //get user's input

//                    editCounter.setName(name);
//                    //Question: Do we need to reset the date to current date when the init value is changed?
//                    if (currentvalue!=editCounter.getCurrentValue()) {
//                        editCounter.setDate();
//                    }
//
//                    editCounter.setInitialValue(initvalue);
//                    editCounter.setCurrentValue(currentvalue);
//                    editCounter.setComment(comment);
//                    Intent intent = new Intent();
//                    intent.putExtra("EDITEDCOUNTER", editCounter);
//                    setResult(RESULT_OK, intent);
//                    finish();
                    //shut down this activity
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
