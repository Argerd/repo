package com.example.user.javacoretraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.javacoretraining.classes.ClassesBlockAbonent;
import com.example.user.javacoretraining.classes.Java.Part2.Directions;
import com.example.user.javacoretraining.classes.Java.Part2.Lambda;
import com.example.user.javacoretraining.classes.VI.Department;
import com.example.user.javacoretraining.classes.VI.Enrolle;
import com.example.user.javacoretraining.classes.VI.Exams;
import com.example.user.javacoretraining.classes.VI.System;
import com.example.user.javacoretraining.classes.VI.Teacher;
import com.example.user.javacoretraining.classes.VII.Client;
import com.example.user.javacoretraining.classes.VII.Merchandiser;
import com.example.user.javacoretraining.classes.VII.Sale;
import com.example.user.javacoretraining.collections.CollectionsBlock;
import com.example.user.javacoretraining.collections.Student;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.user.javacoretraining.classes.Java.Part2.Directions.DOWN;
import static com.example.user.javacoretraining.classes.Java.Part2.Directions.LEFT;
import static com.example.user.javacoretraining.classes.Java.Part2.Directions.RIGHT;
import static com.example.user.javacoretraining.classes.Java.Part2.Directions.UP;

public class MainActivity extends AppCompatActivity {
    private static final String ABONENT_TAG = "ClassesBlockAbonent";
    private static final String DEPARTMENT_TAG = "department";
    private static final String LAMBDA_TAG = "lambda";
    private static final String COORDINATES_TAG = "coordinates";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Student student = new Student();
        List<Student> list = student.getList();
        CollectionsBlock.averageMarks(list);

        /*Создать массив объектов данного класса Абонент*/
        ArrayList<ClassesBlockAbonent> abonents = new ArrayList<>(5);
        abonents.add(new ClassesBlockAbonent("Simonov", "Andrei",
                "Alekseevich", "a", 555, 21, 3,
                LocalTime.of(0, 0, 0),
                LocalTime.of(0, 33, 44)));
        abonents.add(new ClassesBlockAbonent("Buranov", "Andrei",
                "Alekseevich", "a", 555, 21, 3,
                LocalTime.of(4, 20, 33),
                LocalTime.of(1, 33, 44)));
        abonents.add(new ClassesBlockAbonent("Barinov", "Andrei",
                "Alekseevich", "a", 555, 21, 3,
                LocalTime.of(0, 0, 0),
                LocalTime.of(2, 33, 44)));
        abonents.add(new ClassesBlockAbonent("Sysoev", "Andrei",
                "Alekseevich", "a", 555, 21, 3,
                LocalTime.of(4, 20, 33),
                LocalTime.of(3, 33, 44)));
        abonents.add(new ClassesBlockAbonent("Bateyschikov", "Andrei",
                "Alekseevich", "a", 555, 21, 3,
                LocalTime.of(4, 20, 33),
                LocalTime.of(6, 33, 44)));



        /*Вывести сведения относительно абонентов, у которых время городских переговоров
        превышает заданное.*/
        Log.d(ABONENT_TAG, "Вывести сведения относительно абонентов," +
                " у которых время городских переговоров\n" +
                "        превышает заданное");
        for (ClassesBlockAbonent item : abonents) {
            if (item.getTimeOfInCityCalls().isAfter(LocalTime.of(1, 0))) item.print();
        }

        /*Сведения относительно абонентов, которые пользовались
        междугородной связью.*/
        Log.d(ABONENT_TAG, "Сведения относительно абонентов, которые пользовались\n" +
                "      междугородной связью.");
        for (ClassesBlockAbonent item : abonents) {
            if (item.getTimeOfTransCityCalls().isAfter(LocalTime.of(0, 0, 0)))
                item.print();
        }

        Collections.sort(abonents, (abonent, abonent1) ->
                abonent.getLastName().compareTo(abonent1.getLastName()));
        Log.d(ABONENT_TAG, "Список абонентов в алфавитном порядке");
        for (ClassesBlockAbonent item : abonents) {
            item.print();
        }

        /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */
        Enrolle enrolle = new Enrolle("Simonov", "2", "1");
        Enrolle enrolle1 = new Enrolle("Barinov", "1", "2");

        Department department = new Department();
        enrolle.register(department);
        enrolle1.register(department);

        Exams exams = new Exams(enrolle.getFirstAnswer(), enrolle.getSecondAnswer());
        Exams exams1 = new Exams(enrolle1.getFirstAnswer(), enrolle1.getSecondAnswer());

        Teacher teacher = new Teacher();
        teacher.setMarkFirst(exams);
        teacher.setMarkSecond(exams);
        teacher.setMarkFirst(exams1);
        teacher.setMarkSecond(exams1);

        System.result(department, exams, enrolle);
        System.result(department, exams1, enrolle1);

        List<Enrolle> listE = department.getSucsessEnrolles();
        for (int i = 0; i < listE.size(); i++) {
            Log.d(DEPARTMENT_TAG, listE.get(i).getName());
        }

        /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */

        Merchandiser merchandiser = new Merchandiser();
        merchandiser.addProduct(20, 10);
        merchandiser.addProduct(13, 48);
        merchandiser.addProduct(22, 12);

        Client client = new Client();
        Client client1 = new Client();

        client.getOrder().addProduct(merchandiser.getProduct(1));
        client1.getOrder().addProduct(merchandiser.getProduct(2));

        Sale sale = new Sale();
        Sale sale1 = new Sale();

        merchandiser.registerSale(sale, client, client.pay(true));
        merchandiser.registerSale(sale1, client1, client1.pay(false));

        Lambda myClosure = () -> Log.d(LAMBDA_TAG, "I love Java");
        //myClosure.iLoveJava();

        repeatTask(myClosure, 10);

        int[] location = {0, 0};
        move(location);
    }

    public void repeatTask(Lambda lambda, int times) {
        for (int i = 0; i < times; i++) {
            //Log.d(LAMBDA_TAG, "" + i + " ");
            lambda.iLoveJava();
        }
    }

    public int[] oneMove(int[] coordinates, Directions directions) {
        switch (directions) {
            case DOWN:
                coordinates[1]--;
                break;
            case UP:
                coordinates[1]++;
                break;
            case LEFT:
                coordinates[0]--;
                break;
            case RIGHT:
                coordinates[0]++;
                break;
        }
        return coordinates;
    }

    public void move(int[] location) {
        Directions[] directions = {UP, UP, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
        for (int i = 0; i < directions.length; i++) {
            switch (directions[i]) {
                case RIGHT:
                case DOWN:
                case LEFT:
                case UP:
                    location = oneMove(location, directions[i]);
                    break;
            }
        }
        Log.d(COORDINATES_TAG, Arrays.toString(location));
    }
}
