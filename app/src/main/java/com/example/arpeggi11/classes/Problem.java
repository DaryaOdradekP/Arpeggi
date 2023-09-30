package com.example.arpeggi11.classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
Класс, отвечающий за проблемы пользователя (чтобы пользователь отмечал свое настроение, причину настроения, степень)
 */

public class Problem implements Parcelable {

    String login, problem, degree, key, time, text;
    List<String> reason;

    public Problem() {
    }

    public Problem(String login, String problem) {
        this.login = login;
        this.problem = problem;
    }

    public Problem(String login, String problem, String degree, ArrayList<String> reason) {
        this.login = login;
        this.problem = problem;
        this.degree = degree;
        this.reason = reason;
    }

    protected Problem(Parcel in) {
        login = in.readString();
        problem = in.readString();
        degree = in.readString();
        key = in.readString();
        reason = in.createStringArrayList();
        time = in.readString();
        text = in.readString();
    }

    public static final Creator<Problem> CREATOR = new Creator<Problem>() {
        @Override
        public Problem createFromParcel(Parcel in) {
            return new Problem(in);
        }

        @Override
        public Problem[] newArray(int size) {
            return new Problem[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<String> getReason() {
        return reason;
    }

    public void setReason(List<String> reason) {
        this.reason = reason;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(problem);
        dest.writeString(degree);
        dest.writeString(key);
        dest.writeStringList(reason);
    }
}
