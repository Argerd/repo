package com.example.user.javacoretraining.classes.Java.Part2;

public class Square implements Shape {
    private double lengthSide;

    @Override
    public double perimeter() {
        return lengthSide * 4;
    }

    @Override
    public double area() {
        return lengthSide * lengthSide;
    }
}
