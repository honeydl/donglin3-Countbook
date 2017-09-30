package com.example.donglin_countbook;

/**
 * Created by lenovo on 2017/9/30.
 */
import android.content.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class InputOutputGson {
    // Code taken from http://stackoverflow.com/questions/30929838/cannot-resolve-method-openfileoutputjava-lang-string-int
    protected Context context;

    public InputOutputGson(Context context) {
        this.context = context;
    }

    protected ArrayList<Counter> loadFromAllFiles() {
        ArrayList<Counter> counterList = new ArrayList<>();
        Counter counterObj;
        // https://developer.android.com/reference/android/content/Context.html#fileList()
        String[] fileList = context.fileList();
        for (int i = 1; i < fileList.length; ++i) {
            counterList.add(loadFromFile(fileList[i]));
        }
        return counterList;
    }

    protected Counter loadFromFile(String fileName) {
        Counter counterObj;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            counterObj = gson.fromJson(in, Counter.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException();
        }

        return counterObj;
    }

    private String jsonFileName(Counter counter) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(counter.getCounterDate().getTime()) + ".json";
    }

    protected void deleteFile(Counter counter) {
        String fileName = jsonFileName(counter);
        context.deleteFile(fileName);
    }

    protected void saveInFile(Counter counter) {
        String fileName = jsonFileName(counter);
        try {
            FileOutputStream fos = context.openFileOutput(fileName, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counter, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // Code taken from http://stackoverflow.com/questions/22248311/how-to-handle-try-catch-exception-android
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException();
        }
    }
}