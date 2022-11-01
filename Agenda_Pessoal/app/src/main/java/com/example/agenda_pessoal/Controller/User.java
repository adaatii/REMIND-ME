package com.example.agenda_pessoal.Controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Parcelable {
    public String name;
    private String email;
    public String phone;
    private String password;

    public User(){}

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


    public void NewUser(String newName, String newEmail, String newPhone, String newPassword){
        name = newName;
        email = newEmail;
        phone = newPhone;
        password = newPassword;
    }

    public boolean validatePassword(String pass, String confirmPass){ // autenticação de senha (falta tamanho)
        return pass.equals(confirmPass); }

    public boolean authenticateEmail(String authEmail){
        boolean emailIsValid = false;
        if (authEmail != null &&  authEmail.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(authEmail);
            if (matcher.matches()) {
                emailIsValid = true;
            }
        }

        return emailIsValid;
    }

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
