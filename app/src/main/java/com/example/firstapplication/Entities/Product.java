package com.example.firstapplication.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable{
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    float price;
    @SerializedName("quantityInStock")
    Integer quantityInStock;
    @SerializedName("totalQuantity")
    Integer totalQuantity;
    @SerializedName("description")
    String description;
    @SerializedName("categoryEntity")
    Category category;
    @SerializedName("oldPrice")
    String oldPrice;
    @SerializedName("imageUrl")
    String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
