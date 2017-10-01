package com.example.donglin_countbook;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/9/29.
 */

public class Counter implements Parcelable{
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

    public Counter(Parcel parcel){
        this.name = parcel.readString();
        this.comment = parcel.readString();
        this.initialValue = parcel.readInt();
        this.currentValue = parcel.readInt();
        long tmpDate = parcel.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);


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

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCounterDate() {
        return this.date;
    }

    public String getDateString(){
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
        return this.getName()+" | +Count:"+String.valueOf(this.getCurrentValue())+" | "+ this.getDateString();
    }
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(this.name);
        parcel.writeString(this.comment);
        parcel.writeInt(this.initialValue);
        parcel.writeInt(this.currentValue);
        parcel.writeLong(date != null ? date.getTime() : -1);
    }

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
