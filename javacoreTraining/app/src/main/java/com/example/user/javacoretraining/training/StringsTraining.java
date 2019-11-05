package com.example.user.javacoretraining.training;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 != 0) {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        int counter = 0;
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(text.length() - 1) == text.charAt(i)) {
                counter++;
                numbers.append(i);
            }
        }
        int[] array;
        if (counter > 0) {
            array = new int[counter - 1];
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.valueOf(numbers.substring(i, i + 1));
                System.out.print(array[i] + " ");
            }
        } else {
            array = new int[0];
        }
        return array;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int value : alphabet) {
                if (text.charAt(i) == value) {
                    counter++;
                    break;
                }
            }
        }
        System.out.println(counter);
        return counter;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цыфры заменены словами
     */
    public String replaceAllNumbers(String text) {
        int[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] alphabet1 = {"zero",
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (text.charAt(i) == alphabet[j]) {
                    text = text.replaceAll(String.valueOf(text.charAt(i)), alphabet1[j]);
                    break;
                }
            }
        }
        return text;
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                text = text.replace(text.charAt(i), Character.toLowerCase(text.charAt(i)));
            } else {
                text = text.replace(text.charAt(i), Character.toUpperCase(text.charAt(i)));
            }
        }
        return text;
    }
}
