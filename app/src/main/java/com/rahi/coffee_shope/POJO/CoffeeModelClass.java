package com.rahi.coffee_shope.POJO;

public class CoffeeModelClass {

    int image;
    double price;
    String title;

    public CoffeeModelClass(int image, double price, String title) {
        this.image = image;
        this.price = price;
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
