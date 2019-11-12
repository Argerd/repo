package com.example.user.javacoretraining.classes.VI;

import com.example.user.javacoretraining.classes.VI.Enrolle;

import java.util.ArrayList;
import java.util.List;

public class Department {
    List<Enrolle> enrolles = new ArrayList<>();
    List<Enrolle> sucsessEnrolles = new ArrayList<>();

    public void registerEnroll(Enrolle enrolle) {
        enrolles.add(enrolle);
    }

    public void addToSuccessList(Enrolle enrolle) {
        sucsessEnrolles.add(enrolle);
    }

    public List<Enrolle> getEnrolles() {
        return enrolles;
    }

    public void setEnrolles(List<Enrolle> enrolles) {
        this.enrolles = enrolles;
    }

    public List<Enrolle> getSucsessEnrolles() {
        return sucsessEnrolles;
    }

    public void setSucsessEnrolles(List<Enrolle> sucsessEnrolles) {
        this.sucsessEnrolles = sucsessEnrolles;
    }
}
