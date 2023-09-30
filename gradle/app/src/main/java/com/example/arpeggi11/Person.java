package com.example.arpeggi11;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
/*
Класс, отвечающий за данные пользователя (кей, имя, логин, пароль)
 */
public class Person implements Serializable, Parcelable {
    private String key, name, login, password;

    public Person() {
    }

    public Person(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    protected Person(Parcel in) {
        key = in.readString();
        name = in.readString();
        login = in.readString();
        password = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(login);
        dest.writeString(password);
    }
}
