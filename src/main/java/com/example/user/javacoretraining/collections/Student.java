package com.example.user.javacoretraining.collections;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String lastName;
    private String name;
    private String middleName;
    private int dateOfBirth;
    private int course;
    private int numberOfGroup;
    private int markMaths;
    private int markRusLang;
    private int markHistory;
    private int markPhysic;
    private int markJava;

    List<Student> list;

    public Student() {
    }

    public Student(String lastName, String name, String middleName, int dateOfBirth,
                   int course, int numberOfGroup, int markMaths, int markRusLang,
                   int markHistory, int markPhysic, int markJava) {
        this.lastName = lastName;
        this.name = name;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.course = course;
        this.numberOfGroup = numberOfGroup;
        this.markMaths = markMaths;
        this.markRusLang = markRusLang;
        this.markHistory = markHistory;
        this.markPhysic = markPhysic;
        this.markJava = markJava;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public int getMarkMaths() {
        return markMaths;
    }

    public void setMarkMaths(int markMaths) {
        this.markMaths = markMaths;
    }

    public int getMarkRusLang() {
        return markRusLang;
    }

    public void setMarkRusLang(int markRusLang) {
        this.markRusLang = markRusLang;
    }

    public int getMarkHistory() {
        return markHistory;
    }

    public void setMarkHistory(int markHistory) {
        this.markHistory = markHistory;
    }

    public int getMarkPhysic() {
        return markPhysic;
    }

    public void setMarkPhysic(int markPhysic) {
        this.markPhysic = markPhysic;
    }

    public int getMarkJava() {
        return markJava;
    }

    public void setMarkJava(int markJava) {
        this.markJava = markJava;
    }

    public List<Student> getList() {
        list = new ArrayList<>();
        list.add(new Student("Симонов", "Андрей", "Алексеевич",
                1997, 4, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Франк", "Илья", "Алексеевич",
                1995, 2, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Грачев", "Сергей", "Алексеевич",
                1993, 3, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Кульков", "Андрей", "Алексеевич",
                1997, 5, 3, 3, 5, 5,
                4, 4));
        list.add(new Student("Князев", "Андрей", "Алексеевич",
                1988, 2, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Артемьев", "Андрей", "Алексеевич",
                1914, 1, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Баринов", "Андрей", "Алексеевич",
                1920, 1, 3, 2, 5, 5,
                4, 4));
        list.add(new Student("Пилецкий", "Андрей", "Алексеевич",
                1997, 5, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Сысоев", "Андрей", "Алексеевич",
                1997, 3, 3, 5, 5, 5,
                4, 4));
        list.add(new Student("Батейщиков", "Андрей", "Алексеевич",
                1997, 2, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Кондрахин", "Андрей", "Алексеевич",
                1997, 1, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Андреев", "Андрей", "Алексеевич",
                1997, 4, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Казаков", "Андрей", "Алексеевич",
                1997, 3, 3, 4, 5, 5,
                4, 4));
        list.add(new Student("Титов", "Андрей", "Алексеевич",
                1997, 3, 3, 4, 5, 5,
                4, 4));
        CollectionsBlock.sort(list);
        return list;
    }
}
