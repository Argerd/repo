package com.example.user.javacoretraining.classes.VI;

public class Exams {
    private String firstSubjectAnswer;
    private String secondSubjectAnswer;

    private Mark markFirst;
    private Mark markSecond;

    public Exams(String firstSubjectAnswer, String secondSubjectAnswer) {
        this.firstSubjectAnswer = firstSubjectAnswer;
        this.secondSubjectAnswer = secondSubjectAnswer;
    }

    public String getFirstSubjectAnswer() {
        return firstSubjectAnswer;
    }

    public void setFirstSubjectAnswer(String firstSubjectAnswer) {
        this.firstSubjectAnswer = firstSubjectAnswer;
    }

    public String getSecondSubjectAnswer() {
        return secondSubjectAnswer;
    }

    public void setSecondSubjectAnswer(String secondSubjectAnswer) {
        this.secondSubjectAnswer = secondSubjectAnswer;
    }

    public void setMarkFirst(Mark markFirst) {
        this.markFirst = markFirst;
    }

    public Mark getMarkFirst() {
        return markFirst;
    }

    public Mark getMarkSecond() {
        return markSecond;
    }

    public void setMarkSecond(Mark markSecond) {
        this.markSecond = markSecond;
    }
}
