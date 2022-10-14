package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String name;
    private String email;
    public String phone;
    private String password;

    public User(String newName, String newEmail, String newPhone, String newPassword){
        name = newName;
        email = newEmail;
        phone = newPhone;
        password = newPassword;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //Getters
    public String getEmail() {
        return email;
    }
    public String getPassword() { return password; }

    //Setters
    public void setEmail(String novoEmail) {email = novoEmail;}
    public void setPassword(String novoPassword) { password = novoPassword; }

    public String serialize(){
        String serialize;
        serialize = "{" +
                "\"userEmail\":" + "\"" + email + "\", " +
                "\"userName\":" + "\"" + name + "\", " +
                "\"userPassword\":" + "\"" + password + "\", " +
                "\"userPhone\":" + "\"" + phone + "\" "+
                "}";
        return serialize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(password);
    }
}
