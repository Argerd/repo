package com.example.user.javacoretraining.classes;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SecondFromClassesBlock {
    private static final String TAG = "SecondFromClassesBlock";

    private ArrayList<Integer> array = new ArrayList<>();
    private int size = array.size();

    public SecondFromClassesBlock() {
    }

    public SecondFromClassesBlock(int size) {
        this.size = size;
        array = new ArrayList<>(size);
    }

    public void addRandomValues() {
        for (int i = 0; i < array.size(); i++) {
            array.add(new Random().nextInt());
        }
    }

    public void randomSwap() {
        Random random = new Random();
        Collections.swap(array, random.nextInt(array.size() - 1),
                random.nextInt(array.size() - 1));
    }

    public int search(int value) {
        int count = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == value) count++;
        }
        return count;
    }

    public void printArray() {
        for (int item : array) {
            Log.d(TAG, "" + item);
        }
    }
}
