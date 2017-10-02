/*
 * Gson
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

/**
 * This activity
 */
public class InputOutputGson {
    protected Context context;

    public InputOutputGson(Context context) {
        this.context = context;
    }

    /**
     * Create FileList: one counter one file
     * load each counter by load each file in the file list
     * @return counterList
     */
    protected ArrayList<Counter> loadFromAllFiles() {
        ArrayList<Counter> counterList = new ArrayList<>();
        Counter counterObj;
        String[] fileList = context.fileList();
        for (int i = 1; i < fileList.length; ++i) {
            counterList.add(loadFromFile(fileList[i]));
        }
        return counterList;
    }

    /**
     * load counter info from each file
     * @param fileName
     * @return counterList
     */
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

    /**
     * get counter's jsonFileName
     * @param counter
     * @return
     */
    private String jsonFileName(Counter counter) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return dateFormat.format(counter.getCounterDate().getTime()) + ".json";
    }

    /**
     * delete a counter from file
     * @param counter
     */
    protected void deleteFile(Counter counter) {
        String fileName = jsonFileName(counter);
        context.deleteFile(fileName);
    }

    /**
     * save information in file
     * @param counter
     */
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