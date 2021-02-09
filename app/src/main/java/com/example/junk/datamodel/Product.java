package com.example.junk.datamodel;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String id;
    private String image;
    private String name;
    private String style;
    private float unitprice;
    @SerializedName("qty") private int quantity;
    private ProductColor color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductColor getColor() {
        return color;
    }

    public void setColor(ProductColor color) {
        this.color = color;
    }
}
