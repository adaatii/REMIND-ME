package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private int owner;
    public Event event;
    public String description;
    private String title;
    public boolean finished;

    public Task(String title, int owner) {
        this.event = null;
        this.title = title;
        this.finished = false;
        this.owner = owner;
    }

    protected Task(Parcel in) {
        owner = in.readInt();
        event = in.readParcelable(Event.class.getClassLoader());
        description = in.readString();
        title = in.readString();
        finished = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public boolean getOwner(int id){return (owner == id);} //verifica se é o dono
    public boolean isEvent(){return (event != null);} //verifica se é um evento

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"Title\":" + "\"" + title + "\", " +
                "\"Owner\":" + "\"" + owner + "\", " +
                "\"Description\":" + "\"" + description + "\", " +
                "\"Finished\":" + "\"" + (finished ? "true" : "false") + "\"," +
                "\"Event\":" + "\"" + (event == null? "null" : (event.serialize())) + "\"" +
                "}";
        return serialize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(owner);
        parcel.writeParcelable(event, i);
        parcel.writeString(description);
        parcel.writeString(title);
        parcel.writeByte((byte) (finished ? 1 : 0));
    }
}

