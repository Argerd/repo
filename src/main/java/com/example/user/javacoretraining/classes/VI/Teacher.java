package com.example.user.javacoretraining.classes.VI;

import com.example.user.javacoretraining.classes.VI.Exams;
import com.example.user.javacoretraining.classes.VI.Mark;

public class Teacher {
    private static final String TRUE_FIRST_ANSWER = "1";
    private static final String TRUE_SECOND_ANSWER = "2";

    public void setMarkFirst(Exams exams) {
        if (exams.getFirstSubjectAnswer().equals(TRUE_FIRST_ANSWER)) {
            exams.setMarkFirst(new Mark(1));
        } else {
            exams.setMarkFirst(new Mark(0));
        }
    }

    public void setMarkSecond(Exams exams) {
        if (exams.getSecondSubjectAnswer().equals(TRUE_SECOND_ANSWER)) {
            exams.setMarkSecond(new Mark(1));
        } else {
            exams.setMarkSecond(new Mark(0));
        }
    }
}
