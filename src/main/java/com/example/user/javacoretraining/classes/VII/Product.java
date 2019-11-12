package com.example.user.javacoretraining.classes.VII;

public class Product {
    private double price;
    private double size;

    public Product(double price, double size) {
        this.price = price;
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
