package com.example.user.javacoretraining.classes.Java.Part2;

public class Rectangle implements Shape {
    private double length;
    private double width;

    @Override
    public double perimeter() {
        return length + width;
    }

    @Override
    public double area() {
        return length + width;
    }
}
