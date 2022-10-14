package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

public class Relationship implements Parcelable {
    private int[] id;
    private boolean accept;
    private boolean refuse;

    public Relationship(int id1, int id2) {
        this.id = new int[]{id1,id2};
    }

    protected Relationship(Parcel in) {
        id = in.createIntArray();
        accept = in.readByte() != 0;
        refuse = in.readByte() != 0;
    }

    public static final Creator<Relationship> CREATOR = new Creator<Relationship>() {
        @Override
        public Relationship createFromParcel(Parcel in) {
            return new Relationship(in);
        }

        @Override
        public Relationship[] newArray(int size) {
            return new Relationship[size];
        }
    };

    public int[] getId() {
        return id;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isRefuse() {
        return refuse;
    }

    public void setRefuse(boolean refuse) {
        this.refuse = refuse;
    }

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"ID\": [" + "\"" + id[0] + "\", " + "\"" + id[1] + "\"], " +
                "\"Accept\":" + "\"" + (accept ? "true" : "false") + "\"," +
                "\"Refuse\":" + "\"" + (refuse ? "true" : "false") + "\"" +
                "}";
        return serialize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(id);
        parcel.writeByte((byte) (accept ? 1 : 0));
        parcel.writeByte((byte) (refuse ? 1 : 0));
    }
}
