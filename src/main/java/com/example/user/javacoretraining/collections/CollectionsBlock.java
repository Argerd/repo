package com.example.user.javacoretraining.collections;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    private static final String TAG = "CollectionsBlock";

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        List<T> list = new ArrayList<>();
        list.addAll(firstList);
        list.addAll(secondList);
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            result.add(inputList.get(i));
            for (int j = 0; j < i; j++) {
                result.add(inputList.get(j));
            }
        }
        return result;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        boolean result = false;
        int counter = 0;
        if (firstList.size() == 0 && secondList.size() == 0) return true;
        for (int i = 0; i < firstList.size(); i++) {
            for (int j = 0; j < secondList.size(); j++) {
                if (firstList.get(i) == secondList.get(j)) {
                    counter = 0;
                    result = true;
                    break;
                } else {
                    counter++;
                    System.out.println(counter);
                    if (counter == secondList.size() - 1) return false;
                }
            }
        }
        return result;
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        List<T> list = new ArrayList<>(inputList);
        if (list.size() == 0) return list;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                list.add(0, list.get(list.size() - 1));
                list.remove(list.size() - 1);
            }
        } else {
            if (n < 0) {
                n = -n;
                for (int i = 0; i < n; i++) {
                    list.add(list.get(0));
                    list.remove(0);
                }
            }
        }
        return list;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i).equals(a)) inputList.set(i, b);
        }
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    public static void sort(List<Student> inputList) {
        Collections.sort(inputList, (student, t1) ->
                Integer.compare(student.getCourse(), t1.getCourse()));

        for (int i = 0; i < inputList.size(); i++) {
            if (i != inputList.size() - 1)
                if (inputList.get(i).getCourse() == inputList.get(i + 1).getCourse())
                    if (inputList.get(i).getLastName()
                            .compareTo(inputList.get(i + 1).getLastName()) > 0) {
                        Collections.swap(inputList, i, i + 1);
                    }
        }
    }

    public static void averageMarks(List<Student> inputList) {
        Log.d(TAG, "Average");
        int counter = 0;
        int sumMath = 0;
        int sumRusLang = 0;
        int sumHistory = 0;
        int sumPhisyc = 0;
        int sumJava = 0;
        for (int i = 0; i < inputList.size(); i++) {
            if (i != inputList.size() - 1) {
                if (inputList.get(i).getNumberOfGroup()
                        == inputList.get(i + 1).getNumberOfGroup()) {
                    counter++;
                    sumMath += inputList.get(i).getMarkMaths();
                    sumRusLang += inputList.get(i).getMarkRusLang();
                    sumHistory += inputList.get(i).getMarkHistory();
                    sumPhisyc += inputList.get(i).getMarkPhysic();
                    sumJava += inputList.get(i).getMarkJava();
                } else {
                    counter++;
                    sumMath += inputList.get(i).getMarkMaths();
                    sumRusLang += inputList.get(i).getMarkRusLang();
                    sumHistory += inputList.get(i).getMarkHistory();
                    sumPhisyc += inputList.get(i).getMarkPhysic();
                    sumJava += inputList.get(i).getMarkJava();

                    sumMath = sumMath / counter;
                    sumRusLang = sumRusLang / counter;
                    sumHistory = sumHistory / counter;
                    sumPhisyc = sumPhisyc / counter;
                    sumJava = sumJava / counter;
                    counter = 0;
                    Log.d(TAG, "average marks " + inputList.get(i).getNumberOfGroup() + " group");
                    Log.d(TAG, "" + sumMath + " " + sumRusLang +
                            " " + sumHistory + " " + sumPhisyc + " " + sumJava);
                    sumMath = 0;
                    sumRusLang = 0;
                    sumHistory = 0;
                    sumPhisyc = 0;
                    sumJava = 0;
                }
            }
        }
    }

    /**
     * Определите самого старшего студента и самого младшего студентов.
     */
    public static void most(List<Student> inputList) {
        Student old = inputList.get(0);
        Student young = inputList.get(0);
        for (int i = 1; i < inputList.size(); i++) {
            if (inputList.get(i).getDateOfBirth() > old.getDateOfBirth()) old = inputList.get(i);

            if (inputList.get(i).getDateOfBirth() < young.getDateOfBirth())
                young = inputList.get(i);
        }
    }

    /**
     * Для каждой группы найдите лучшего с точки зрения успеваемости студента
     */
    public static void bests(List<Student> inputList) {
        List<Student> bests = new LinkedList<>();
        double average = 0;
        int number = 0;
        for (int i = 0; i < inputList.size() - 1; i++) {
            if (inputList.get(i).getNumberOfGroup() == inputList.get(i + 1).getNumberOfGroup()) {
                double t = (inputList.get(i).getMarkHistory() + inputList.get(i).getMarkJava()
                        + inputList.get(i).getMarkMaths() + inputList.get(i).getMarkPhysic()
                        + inputList.get(i).getMarkRusLang()) / 5;
                if (t > average) {
                    number = i;
                }
            } else {
                double t = (inputList.get(i).getMarkHistory() + inputList.get(i).getMarkJava()
                        + inputList.get(i).getMarkMaths() + inputList.get(i).getMarkPhysic()
                        + inputList.get(i).getMarkRusLang()) / 5;
                if (t > average) {
                    number = i;
                }
                average = 0;
                bests.add(inputList.get(i));
            }
        }
    }
}
