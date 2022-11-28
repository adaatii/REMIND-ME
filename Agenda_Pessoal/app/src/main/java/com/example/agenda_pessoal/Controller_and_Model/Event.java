package com.example.agenda_pessoal.Controller_and_Model;

import android.os.Parcel;
import android.os.Parcelable;


public class Event implements Parcelable {
    public String[] date;

    public Event(String[] dateTime) {
        date = dateTime;
    }

    protected Event(Parcel in) {
        date = in.createStringArray();
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

    public String serialize() {
        String serialize;
        serialize = "[\"" + date[0] + "\",\"" + date[1] + "\"]"; // serialize = date.toString();
        return serialize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(date);
    }
}
