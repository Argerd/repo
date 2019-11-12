package com.example.user.javacoretraining.classes;

import android.util.Log;

public class ClassesBlockTriangle {
    private static final String TAG = "ClassesBlockTriangle";

    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;

    private double sideA;
    private double sideB;
    private double sideC;

    public ClassesBlockTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        firstPoint = new Point(x1, y1);
        secondPoint = new Point(x2, y2);
        thirdPoint = new Point(x3, y3);
        calculateSides();
    }

    public ClassesBlockTriangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
        calculateSides();
    }

    private void calculateSides() {
        sideA = Math.sqrt(Math.pow((secondPoint.getX() - firstPoint.getX()), 2)
                + Math.pow((secondPoint.getY() - firstPoint.getY()), 2));
        if (sideA < 0) sideA = -sideA;
        sideB = Math.sqrt(Math.pow((thirdPoint.getX() - secondPoint.getX()), 2)
                + Math.pow((thirdPoint.getY() - secondPoint.getY()), 2));
        if (sideB < 0) sideB = -sideB;
        sideC = Math.sqrt(Math.pow((thirdPoint.getX() - firstPoint.getX()), 2)
                + Math.pow((thirdPoint.getY() - firstPoint.getY()), 2));
        if (sideC < 0) sideC = -sideC;
    }

    public double perimeter() {
        return sideA + sideB + sideC;
    }

    public double square() {
        double halfPerimeter = perimeter() / 2;
        return Math.sqrt(halfPerimeter
                * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC));
    }

    public Point pointOfMedianes() {
        return new Point((firstPoint.getX() + secondPoint.getX() + thirdPoint.getX()) / 3,
                (firstPoint.getY() + secondPoint.getY() + thirdPoint.getY()) / 3);
    }

    public double xOfPointOfMedianes() {
        return (firstPoint.getX() + secondPoint.getX() + thirdPoint.getX()) / 3;
    }

    public double yOfPointOfMedianes() {
        return (firstPoint.getY() + secondPoint.getY() + thirdPoint.getY()) / 3;
    }

    public void printState() {
        Log.d(TAG, "state");
        Log.d(TAG, "firstPoint x: " + firstPoint.getX() + " y: " + firstPoint.getY());
        Log.d(TAG, "secondPoint x: " + secondPoint.getX() + " y: " + secondPoint.getY());
        Log.d(TAG, "thirdPoint x: " + thirdPoint.getX() + " y: " + thirdPoint.getY());
        Log.d(TAG, "sideA: " + sideA);
        Log.d(TAG, "sideB: " + sideB);
        Log.d(TAG, "sideC: " + sideC);
        Log.d(TAG, "perimeter: " + perimeter());
        Log.d(TAG, "square: " + square());
        Log.d(TAG, "point of pointOfMedianes x: " + xOfPointOfMedianes() +
                " y: " + yOfPointOfMedianes());
    }
}
