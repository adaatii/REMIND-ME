package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Data implements Parcelable {
    private ArrayList<User> dataUser = new ArrayList<User>();
    private ArrayList<Task> dataTask = new ArrayList<Task>();
    private ArrayList<Relationship> dataRelationship = new ArrayList<Relationship>();
    public boolean guest;
    public Integer log;

    public Data() {
        initializeValues();
    }


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

    public Integer checkEventTime(String date, String time) {
        Integer id = null;
        for (int i = 0; i < dataTask.size(); i++) {
            Task item = dataTask.get(i);
            if (item.isEvent() && item.getOwner(log)
                    && !item.finished && date.equals(item.event.date[0])
                    && time.equals(item.event.date[1])
            ){
               id = i;

            }
        }
        return id;
    }

    public void Update(Data updatedData) {
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

    private User testUser(String name, String email, String phone, String password) {
        User user = new User();
        user.NewUser(name, email, phone, password);
        return user;
    }

    private Task testEvent(String title, int owner, String description, String[] dateTime) {
        Task event = new Task(title, owner,1);
        event.description = description;
        event.createEvent(dateTime);
        return event;
    }

    private Task testTask(String title, int owner, String description) {
        Task task = new Task(title, owner,2);
        task.description = description;
        return task;
    }

    public void initializeValues() {
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Date time = new Date();

        dataUser.add(testUser("Lucas", "teste@teste.com.br", "1298875748", "123"));
        dataUser.add(testUser("Leandro", "teste2@teste.com.br", "4845456484", "123"));

        dataTask.add(testEvent(
                "Dor e sofrimento",
                0,
                "Na casinha",
                new String[]{"03/11/2022", "06:00"}));

        dataTask.add(testTask(
                "Reunião no Discord",
                0,
                "trabalho de ESII"));

        dataTask.add(testEvent(
                "Trabalho Enari",
                0,
                "",
                new String[]{localDate, "06:00"}));
        dataTask.add(testEvent(
                "Acelera",
                0,
                "Fatec Cruzeiro",
                new String[]{"23/10/2022", "08:00"}));

        dataTask.add(testTask(
                "teste",
                0,
                "trabalho de ESII"));
        dataTask.add(testTask(
                "teste2",
                0,
                ""));
        dataTask.add(testTask(
                "teste3",
                0,
                ""));
        dataRelationship.add(new Relationship(0, 1));
        dataRelationship.add(new Relationship(1, 0));


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

    public String serialize() {
        String serialize;
        serialize = "{\"Data\":{" +
                "\"guest\":" + "\"" + (guest ? "true" : "false") + "\"," +
                "\"log\":" + "\"" + log + "\"," +
                "\"User\": [";
        for (int i = 0; i < dataUser.size(); i++) {
            User data = dataUser.get(i);
            serialize += data.serialize();
            serialize += (dataUser.size() - 1) == i ? "" : ",";
        }
        serialize += "],";

        serialize += "\"Task\": [";

        for (int i = 0; i < dataTask.size(); i++) {
            Task data = dataTask.get(i);
            serialize += data.serialize();
            serialize += (dataTask.size() - 1) == i ? "" : ",";
        }
        serialize += "],";

        serialize += "\"Relationship\": [";

        for (int i = 0; i < dataRelationship.size(); i++) {
            Relationship data = dataRelationship.get(i);
            serialize += data.serialize();
            serialize += (dataRelationship.size() - 1) == i ? "" : ",";
        }
        serialize += "]";

        serialize += "}}";

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
