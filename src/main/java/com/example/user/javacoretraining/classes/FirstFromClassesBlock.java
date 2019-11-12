package com.example.user.javacoretraining.classes;

import android.util.Log;

public class FirstFromClassesBlock {
    private static final String TAG = "FirstFromClassesBlock";

    private int first = 5;
    private int second = 6;

    public void print() {
        Log.d(TAG, "first: " + first + ", second: " + second);
    }

    public void setNewValues(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int sum() {
        return first + second;
    }

    public int biggestValue() {
        return first > second ? first : second;
    }
}
