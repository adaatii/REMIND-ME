package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data implements Parcelable {
    private ArrayList<User> dataUser = new ArrayList<User>();
    private ArrayList<Task> dataTask = new ArrayList<Task>();
    private ArrayList<Relationship> dataRelationship = new ArrayList<Relationship>();
    private boolean guest;
    private Integer log;

    public Data(){initializeValues();}


    protected Data(Parcel in) {
        dataUser = in.createTypedArrayList(User.CREATOR);
        dataTask = in.createTypedArrayList(Task.CREATOR);
        dataRelationship = in.createTypedArrayList(Relationship.CREATOR);
        guest = in.readByte() != 0;
        if (in.readByte() == 0) {
            log = null;
        } else {
            log = in.readInt();
        }
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public void Update(Data updatedData){
        dataUser.clear();
        dataTask.clear();
        dataRelationship.clear();
        guest = updatedData.guest;
        log = updatedData.log;

        ArrayList<User> updatedDataUser = updatedData.getDataUser();
        ArrayList<Task> updatedDataTask = updatedData.getDataTask();
        ArrayList<Relationship> updatedDataRelationship = updatedData.getDataRelationship();

        for (int i = 0; i < updatedDataUser.size(); i++)
            dataUser.add(updatedDataUser.get(i));


        for (int i = 0; i < updatedDataTask.size(); i++)
            dataTask.add(updatedDataTask.get(i));


        for (int i = 0; i < updatedDataRelationship.size(); i++)
            dataRelationship.add(updatedDataRelationship.get(i));

    }
    public void initializeValues(){
        dataUser.add(new User("Lucas","teste@teste","1298875748"
                ,"123"));
        dataUser.add(new User("Leandro","teste2@teste","4845456484"
                ,"123"));
        dataTask.add(new Task("Dor e sofrimento",0));
        dataTask.get(0).description = "Na casinha";
        dataTask.add(new Task("Trabalho Enari",1));
        dataRelationship.add(new Relationship(0,1));
        dataRelationship.add(new Relationship(1,0));

    }

    public ArrayList<User> getDataUser() {
        return dataUser;
    }

    public ArrayList<Task> getDataTask() {
        return dataTask;
    }

    public ArrayList<Relationship> getDataRelationship() {
        return dataRelationship;
    }

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"guest\":" + "\"" + (guest ? "true" : "false") + "\"," +
                "\"log\":" + "\"" + log + "\"," +
                "\"dataUser\": [";
        for (int i = 0; i < dataUser.size(); i++){
            User data = dataUser.get(i);
            serialize += data.serialize();
            serialize += (dataUser.size() - 1) == i ? "" : ",";
        }
        serialize += "],";

        serialize += "\"dataTask\": [";

        for (int i = 0; i < dataTask.size(); i++){
            Task data = dataTask.get(i);
            serialize += data.serialize();
            serialize += (dataTask.size() - 1) == i ? "" : ",";
        }
        serialize += "],";

        serialize += "\"dataRelationship\": [";

        for (int i = 0; i < dataRelationship.size(); i++){
            Relationship data = dataRelationship.get(i);
            serialize += data.serialize();
            serialize += (dataRelationship.size() - 1) == i ? "" : ",";
        }
        serialize += "]";

        serialize += "}";

        return serialize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(dataUser);
        parcel.writeTypedList(dataTask);
        parcel.writeTypedList(dataRelationship);
        parcel.writeByte((byte) (guest ? 1 : 0));
        if (log == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(log);
        }
    }
}
