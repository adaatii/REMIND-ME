package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Event implements Parcelable {
    public Date date;

    public Event() {
        this.date = new Date();
    }

    protected Event(Parcel in) {
        date = (java.util.Date) in.readSerializable();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"Date\":" + "\"" + date.toString() + "\"" + // serialize = date.toString();
                "}";
        return serialize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(date);
    }
}
