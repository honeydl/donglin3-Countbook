package com.example.donglin_countbook;

/**
 * Created by lenovo on 2017/9/30.
 */
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class InputOutputGson {
    // Code taken from http://stackoverflow.com/questions/30929838/cannot-resolve-method-openfileoutputjava-lang-string-int
    protected Context context;
    private static final String FILENAME = "file.sav";

    public InputOutputGson(Context context) {
        this.context = context;
    }


    protected ArrayList<Counter> loadFromAllFile() {
        // TODO maybe need to make counterList a function parameter
        ArrayList<Counter> counterList;
        // https://developer.android.com/reference/android/content/Context.html#fileList()
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counterList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Error " + e.getMessage());
            throw new RuntimeException();
        }
//        catch (IOException e) {
//            System.out.println("Error " + e.getMessage());
//            throw new RuntimeException();
//        }
        return counterList;
    }



//    private String jsonFileName(Counter counter) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//        String fileName = dateFormat.format(counter.getDate().getTime()) + ".json";
//
//        return fileName;
//    }

//    protected void deleteFile(Counter counter) {
//        String fileName = jsonFileName(counter);
//        context.deleteFile(fileName);
//    }

    protected void saveInFile(ArrayList<Counter> counterList) {
//        String fileName = jsonFileName(counter);
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(counterList, writer);
            writer.flush();
            fos.close();
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