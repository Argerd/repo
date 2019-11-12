package com.example.user.javacoretraining.classes.VI;

import com.example.user.javacoretraining.classes.VI.Department;
import com.example.user.javacoretraining.classes.VI.Enrolle;
import com.example.user.javacoretraining.classes.VI.Exams;

public class System {
    public static void result(Department department, Exams exams, Enrolle enrolle) {
        if (exams.getMarkFirst().getMark() == 1 && exams.getMarkSecond().getMark() == 1)
            department.addToSuccessList(enrolle);
    }
}
