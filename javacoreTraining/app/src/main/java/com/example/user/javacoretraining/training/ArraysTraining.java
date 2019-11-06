package com.example.user.javacoretraining.training;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        for (int i = 0; i < valuesArray.length; i++) {
            for (int j = i + 1; j < valuesArray.length; j++) {
                if (valuesArray[i] > valuesArray[j]) {
                    int temp = valuesArray[i];
                    valuesArray[i] = valuesArray[j];
                    valuesArray[j] = temp;
                }
            }
        }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        int value = 0;
        if (values == null) {
            return value;
        } else {
            for (int item : values) {
                if (item > value) {
                    value = item;
                }
            }
        }
        return value;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int[] reverse = new int[array.length];
        for (int i = 0; i < reverse.length; i++) {
            reverse[i] = array[array.length - i - 1];
        }
        for (int i = 0; i < reverse.length; i++) {
            System.out.println(reverse[i] + " ");
        }
        return reverse;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount < 1) {
            return new int[]{};
        } else {
            int[] array = new int[numbersCount];
            int n1 = 1;
            int n2 = 1;
            int n3;
            for (int i = 0; i < array.length; i++) {
                if (i == 0 || i == 1) {
                    array[i] = 1;
                } else {
                    n3 = n1 + n2;
                    n1 = n2;
                    n2 = n3;
                    array[i] = n3;
                }
            }
            return array;
        }
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        array = sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        int counter = 0;
        int max = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                counter++;
            } else {
                if (max < counter) {
                    max = counter + 1;
                }
            }
        }
        return max;
    }
}
