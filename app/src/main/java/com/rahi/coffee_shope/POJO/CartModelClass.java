package com.rahi.coffee_shope.POJO;

public class CartModelClass {

    String title;
    String size;
    int counter;
    double price;

    public CartModelClass(String title, int counter, double price,String size) {
        this.title = title;
        this.counter = counter;
        this.price = price;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
