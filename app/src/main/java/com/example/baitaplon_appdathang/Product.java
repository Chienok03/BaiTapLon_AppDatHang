package com.example.baitaplon_appdathang;

import android.os.Parcelable;

import java.io.Serializable;

public class Product  implements Serializable  {
    private int id;
    private String name;
    private int quantity;
    private int imageUrl;
    private double price;

    public Product() {
        // Empty constructor required for SQLite
    }
    //Contructor
    public Product(int id, String name, int quantity, int imageUrl,double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
