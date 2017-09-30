package com.example.donglin_countbook;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/9/29.
 */

public class Counter {
    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String comment;

    public Counter() {
        date = new java.util.Date();
    }
    public Counter(String name, int initialValue){
        this.setName(name);
        this.setInitialValue(initialValue);
        currentValue = initialValue;
        this.setDate();
        comment = "";
    }

    public Counter(String name, int initialValue, String comment){
        this.setName(name);
        this.setInitialValue(initialValue);
        currentValue = initialValue;
        this.setDate();
        this.comment = comment;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(){
        this.date = new java.util.Date();
    }

    public String getDate(){
        //return date.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
    }

    public int getCurrentValue(){
        return currentValue;
    }

    public boolean setCurrentValue(int currentValue){
        if (currentValue < 0) {
            return false;
        }
        this.currentValue = currentValue;
        return true;
    }
    public int getInitialValue(){
        return initialValue;
    }
    public boolean setInitialValue(int initialValue){
        if (initialValue<0){
            return false;
        }
        this.initialValue = initialValue;
           return true;
    }

    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public boolean decrementValue() {
        if (this.currentValue == 0) {
            return false;
        }
        this.currentValue -= 1;
        return true;
    }

    public void incrementValue() {
        this.currentValue += 1;
    }
    public void resetValue(){
        this.currentValue= this.initialValue;
    }

    @Override
    public String toString(){
        return name+" | +Count:"+String.valueOf(currentValue)+" | "+ this.getDate();
    }

}
