/*
 * Counter
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

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/9/29.
 */

/**
 * Represents a Counter
 * Contain: Name, inital value, current value, date and comment
 * this object Counter implements a spectial interface called Parcelable,
 * this object can be passed between different activities since it implements Parcelable interface
 * Source : https://developer.android.com/reference/android/os/Parcelable.html
 *
 * @author donglin
 * @version 1.0
 * @see Parcelable
 * @since 1.0
 *
 */

public class Counter implements Parcelable{
    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String comment;

    /**
     *Counter with no parameter
     */
    public Counter() {
        date = new java.util.Date();
    }

    /**
     * Counter with name and initalValue
     * @param name  counter name
     * @param initialValue  counter inital value
     */
    public Counter(String name, int initialValue){
        this.setName(name);
        this.setInitialValue(initialValue);
        currentValue = initialValue;
        this.setDate();
        comment = "";
    }

    /**
     * Counter with name and initalValue and comment
     * @param name counter name
     * @param initialValue  counter initial value
     * @param comment  counter comment
     */
    public Counter(String name, int initialValue, String comment){
        this.setName(name);
        this.setInitialValue(initialValue);
        currentValue = initialValue;
        this.setDate();
        this.comment = comment;
    }

    /**
     * Interface for Counter class whose info can be written to and restored from a Parcel.
     * @param parcel
     */
    public Counter(Parcel parcel){
        this.name = parcel.readString();
        this.comment = parcel.readString();
        this.initialValue = parcel.readInt();
        this.currentValue = parcel.readInt();
        long tmpDate = parcel.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);


    }

    /**
     * Getter: name
     * @return counter name
     */
    public String getName(){
        return name;
    }

    /**
     * setter: name
     * @param name counter name
     */

    public void setName(String name){
        this.name = name;
    }

    /**
     * setter: date
     */
    public void setDate(){
        this.date = new java.util.Date();
    }

    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * getter: date
     * @return  counter date
     */
    public Date getCounterDate() {
        return this.date;
    }

    public String getDateString(){
        //return date.toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
    }

    /**
     * getter: current value
     * @return  counter current value
     */
    public int getCurrentValue(){
        return currentValue;
    }

    /**
     * setter: current value
     * @param currentValue counter current value
     * @return
     */
    public boolean setCurrentValue(int currentValue){
        if (currentValue < 0) {
            return false;
        }
        this.currentValue = currentValue;
        return true;
    }

    /**
     * getter: initial value
     * @return counter initial value
     */
    public int getInitialValue(){
        return initialValue;
    }

    /**
     * setter: initial value
     * @param initialValue counter initial value
     * @return
     */
    public boolean setInitialValue(int initialValue){
        if (initialValue<0){
            return false;
        }
        this.initialValue = initialValue;
           return true;
    }

    /**
     * getter: comment
     * @return counter comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * setter: comment
     * @param comment counter comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * print message
     * @return string Text
     */
    @Override
    public String toString(){
        return this.getName()+" | +Count:"+String.valueOf(this.getCurrentValue())+" | "+ this.getDateString();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * source: https://developer.android.com/reference/android/os/Parcelable.html
     * @return
     */
    @Override
    public int describeContents(){
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     * Source: https://developer.android.com/reference/android/os/Parcelable.html
     * @param parcel counter info
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(this.name);
        parcel.writeString(this.comment);
        parcel.writeInt(this.initialValue);
        parcel.writeInt(this.currentValue);
        parcel.writeLong(date != null ? date.getTime() : -1);
    }

    /**
     * Counter implementing the Parcelable interface must also have a non-null static field called CREATOR of a type that implements the Parcelable.Creator interface.
     * source: https://developer.android.com/reference/android/os/Parcelable.html
     * Get consultation with Yongjia Huang
     */
    public static final Parcelable.Creator<Counter> CREATOR = new Parcelable.Creator<Counter>(){
        @Override
        public Counter createFromParcel(Parcel parcel){
            return new Counter(parcel);
        }
        @Override
        public Counter[] newArray(int i){
            return new Counter[i];
        }

    };

}
