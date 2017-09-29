package com.example.donglin_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private TextView total;
    private ListView ViewList;
    private String Name;
    private String Comment;
    private int InitialValue;
    private Counter newCounter;
    private ArrayList<Counter> CounterList = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;





        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            ViewList = (ListView) findViewById(R.id.CounterList); //Create listView reference counterList Which contains all of the counters that user has created;
            ViewList.setOnItemClickListener(this);

            bodyText = (EditText) findViewById(R.id.body);
            Button Add = (Button) convertView.findViewById(R.id.my_btn);


            saveButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    setResult(RESULT_OK);
                    String text = bodyText.getText().toString();

                    NormalTweet newTweet = new NormalTweet("Hello");

                    tweetList.add(newTweet);
                    adapter.notifyDataSetChanged();


                    saveInFile();
                    //finish();


                }
            });


            Button clearButton = (Button) findViewById(R.id.clear);
            clearButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    tweetList.clear();
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
            });

        }
    }
}
