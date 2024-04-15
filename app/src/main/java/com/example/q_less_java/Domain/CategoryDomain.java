package com.example.q_less_java.Domain;


import java.io.Serializable;

// General template for our data set
public class CategoryDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private double price;
    private int numberInCart;

    public CategoryDomain(String title, String pic, String description, double price) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
    }

    public CategoryDomain(String title, String pic, String description, double price, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
