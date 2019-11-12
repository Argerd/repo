package com.example.user.javacoretraining.classes.Java.Part2;

public class Circle implements Shape {
    private double diameter;

    @Override
    public double perimeter() {
        return Math.PI * diameter;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow((diameter / 2), 2);
    }
}

