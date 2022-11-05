package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private int owner;
    public Event event;
    public String description;
    private String title;
    public boolean finished;
    public int priority;

    public Task(String title, int owner, int priority) {
        this.event = null;
        this.title = title;
        this.finished = false;
        this.owner = owner;
        this.priority = priority;
    }

    protected Task(Parcel in) {
        owner = in.readInt();
        event = in.readParcelable(Event.class.getClassLoader());
        description = in.readString();
        title = in.readString();
        finished = in.readByte() != 0;
        priority = in.readInt();
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

    public boolean isEvent(){return (event != null);} //verifica se é um evento

    //Getters
    public String getTitle() {return title;}
    public boolean getOwner(int id){return (owner == id);} //verifica se é o dono
    //Setters
    public void setTitle(String title) {this.title = title;}

    public void createEvent(String[] dataTime){
        this.event = new Event(dataTime);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(owner);
        dest.writeParcelable(event, flags);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeByte((byte) (finished ? 1 : 0));
        dest.writeInt(priority);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"Title\":" + "\"" + title + "\", " +
                "\"Owner\":" + "\"" + owner + "\", " +
                "\"Description\":" + "\"" + description + "\", " +
                "\"Finished\":" + "\"" + (finished ? "true" : "false") + "\"," +
                "\"Event\":" + (event == null? "\"null\"" : (event.serialize())) +
                "\"Priority\":" + "\"" + priority + "\", "+
                "}";
        return serialize;
    }



}

