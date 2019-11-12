package com.example.user.javacoretraining.classes.VI;

public class Enrolle {
    private String name;
    private String firstAnswer;
    private String secondAnswer;

    public Enrolle(String name, String firstAnswer, String secondAnswer) {
        this.name = name;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
    }

    public void register(Department department) {
        department.registerEnroll(this);
    }

    public void passExams(Exams exams) {
        exams.setFirstSubjectAnswer(firstAnswer);
        exams.setSecondSubjectAnswer(secondAnswer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }
}
